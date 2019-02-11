package com.valkessentials.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.valkessentials.ValkEssentials;
import com.valkessentials.utils.Items;
import com.valkutils.modules.ItemModule;

public class Help implements CommandExecutor, Listener {
	Map<String, Integer> handNavTracker = new HashMap<String, Integer>();
	Map<String, Integer> craftingNavTracker = new HashMap<String, Integer>();
	Map<String, Integer> furnaceNavTracker = new HashMap<String, Integer>();
	Map<String, Integer> shapelessNavTracker = new HashMap<String, Integer>();

	Map<String, Integer> handRecipeTracker = new HashMap<String, Integer>();
	Map<String, Integer> craftingRecipeTracker = new HashMap<String, Integer>();
	Map<String, Integer> furnaceRecipeTracker = new HashMap<String, Integer>();
	Map<String, Integer> shapelessRecipeTracker = new HashMap<String, Integer>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender)
			return false;
		if (command.getName().equalsIgnoreCase("help")) {
			Player target = Bukkit.getPlayer(sender.getName());
			target.openInventory(help());
			return true;
		}
		return false;
	}

	private Inventory help() {
		Inventory info = Bukkit.createInventory(null, 27, "Help");
		info.setItem(0, Items.invInfo(Material.BOOK, "Discord", new String[] { "Link to discord." }));
		info.setItem(1, Items.invInfo(Material.BOOK, "Tutorial", new String[] { "Helpful guide." }));
		return info;
	}

	private Inventory recipeHome() {
		Inventory info = Bukkit.createInventory(null, 27, "Recipe Home");
		info.setItem(0, ItemModule.item("Hand Recipes", "Recipes crafted without the need of a crafting table.",
				Material.FEATHER));
		info.setItem(1, ItemModule.item("Furnace Recipes", "Recipes crafted at the furnace.", Material.FURNACE));
		info.setItem(2,
				ItemModule.item("Crafted Recipes", "Recipes crafted at a crafting table.", Material.CRAFTING_TABLE));
		info.setItem(3, ItemModule.item("Shapeless Recipes", "Shapeless recipes.", Material.PRISMARINE_SHARD));
		info.setItem(22, ItemModule.item("Back", "Go Back", Material.BLACK_STAINED_GLASS_PANE));
		return info;
	}

	/*
	 * Recipe home navigation pages.
	 */
	@SuppressWarnings("deprecation")
	public void itemNavigation(InventoryClickEvent event, List<Inventory> navigation, Map<String, Integer> navTracker,
			List<Inventory> recipes, Map<String, Integer> recipeTracker) {
		Player p = (Player) event.getWhoClicked();
		String invName = event.getView().getTitle();
		boolean isAir = event.getCurrentItem().getType().equals(Material.AIR);
		int slot = event.getSlot();
		// handle item home navigation
		if (invName.equals(navigation.get(0).getName())) {
			event.setCancelled(true);
			if (!isAir) {
				switch (slot) {
				case 45:
					if (navTracker.get(p.getName()) >= 1) {
						p.openInventory(navigation.get(navTracker.get(p.getName()) - 1));
						navTracker.put(p.getName(), navTracker.get(p.getName()) - 1);
					}
					break;
				case 49:
					p.openInventory(recipeHome());
					break;
				case 53:
					if (navTracker.get(p.getName()) < navigation.size() - 1) {
						p.openInventory(navigation.get(navTracker.get(p.getName()) + 1));
						navTracker.put(p.getName(), navTracker.get(p.getName()) + 1);
					}
					break;
				default:
					int offset = navTracker.get(p.getName());
					p.openInventory(recipes.get(slot + (offset * 45)));
					recipeTracker.put(p.getName(), slot + (offset * 45));
				}

			}
		}
	}

	/*
	 * Specific recipe pages.
	 */
	@SuppressWarnings("deprecation")
	public void recipeNavigation(InventoryClickEvent event, List<Inventory> navigation, Map<String, Integer> navTracker,
			List<Inventory> recipes, Map<String, Integer> recipeTracker) {
		Player p = (Player) event.getWhoClicked();
		String invName = event.getView().getTitle();
		int slot = event.getSlot();
		// handle recipe navigation
		if (invName.equals(recipes.get(0).getName())) {
			event.setCancelled(true);
			switch (slot) {
			case 18 + 9:
				if (recipeTracker.get(p.getName()) >= 1) {
					p.openInventory(recipes.get(recipeTracker.get(p.getName()) - 1));
					recipeTracker.put(p.getName(), recipeTracker.get(p.getName()) - 1);

				}
				break;
			case 26 + 9:
				if (recipeTracker.get(p.getName()) < recipes.size() - 1) {
					p.openInventory(recipes.get(recipeTracker.get(p.getName()) + 1));
					recipeTracker.put(p.getName(), recipeTracker.get(p.getName()) + 1);
				}
				break;
			case 18 + 9 + 4:
				navTracker.put(p.getName(), 0);
				p.openInventory(navigation.get(0));
				break;
			default:
				break;
			}
		}
	}

	@EventHandler
	private void registerClicks(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		int slot = e.getSlot();

		// handle item navigation
		itemNavigation(e, ItemModule.furnaceNavigation, furnaceNavTracker, ItemModule.translatedFurnaceRecipes,
				furnaceRecipeTracker);

		// all the hand recipes
		itemNavigation(e, ItemModule.handNavigation, handNavTracker, ItemModule.translatedHandRecipes,
				handRecipeTracker);

		// all the crafting recipes
		itemNavigation(e, ItemModule.craftingNavigation, craftingNavTracker, ItemModule.translatedCraftingRecipes,
				craftingRecipeTracker);

		// shapeless recipes
		itemNavigation(e, ItemModule.shapelessNavigation, shapelessNavTracker, ItemModule.translatedShapelessRecipes,
				shapelessRecipeTracker);

		// specific furnace recipe
		recipeNavigation(e, ItemModule.furnaceNavigation, furnaceNavTracker, ItemModule.translatedFurnaceRecipes,
				furnaceRecipeTracker);

		// specific hand recipe
		recipeNavigation(e, ItemModule.handNavigation, handNavTracker, ItemModule.translatedHandRecipes,
				handRecipeTracker);

		// specific crafting recipe
		recipeNavigation(e, ItemModule.craftingNavigation, craftingNavTracker, ItemModule.translatedCraftingRecipes,
				craftingRecipeTracker);

		// specific shapeless recipe
		recipeNavigation(e, ItemModule.shapelessNavigation, shapelessNavTracker, ItemModule.translatedShapelessRecipes,
				shapelessRecipeTracker);

		switch (e.getView().getTitle().toLowerCase()) {
		case "help":
			e.setCancelled(true);

			switch (slot) {
			case 0:
				p.sendMessage(ValkEssentials.getPrefix() + "https://discord.gg/xhXeFhr");
				p.closeInventory();
				break;
			case 1:
				p.openInventory(recipeHome());
				break;
			}
			break;
		case "recipe home":
			switch (slot) {
			case 0:
				handNavTracker.put(p.getName(), 0);
				p.openInventory(ItemModule.handNavigation.get(0));
				break;
			case 1:
				furnaceNavTracker.put(p.getName(), 0);
				p.openInventory(ItemModule.furnaceNavigation.get(0));
				break;
			case 2:
				craftingNavTracker.put(p.getName(), 0);
				p.openInventory(ItemModule.craftingNavigation.get(0));
				break;
			case 3:
				shapelessNavTracker.put(p.getName(), 0);
				p.openInventory(ItemModule.shapelessNavigation.get(0));
				break;
			case 22:
				p.openInventory(help());
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
}
