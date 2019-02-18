package com.valkessentials.commands;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.SkullMeta;

import com.valkutils.modules.ItemModule;
import com.valkutils.modules.TextModule;

public class Help extends Cmd implements Listener {
	Map<String, Integer> handNavTracker = new HashMap<String, Integer>();
	Map<String, Integer> craftingNavTracker = new HashMap<String, Integer>();
	Map<String, Integer> furnaceNavTracker = new HashMap<String, Integer>();
	Map<String, Integer> shapelessNavTracker = new HashMap<String, Integer>();

	Map<String, Integer> handRecipeTracker = new HashMap<String, Integer>();
	Map<String, Integer> craftingRecipeTracker = new HashMap<String, Integer>();
	Map<String, Integer> furnaceRecipeTracker = new HashMap<String, Integer>();
	Map<String, Integer> shapelessRecipeTracker = new HashMap<String, Integer>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		if (!cmd("help", "none", 0)) return false;
		
		Player target = Bukkit.getPlayer(sender.getName());
		target.openInventory(help());
		return true;
	}

	private Inventory help() {
		Inventory info = Bukkit.createInventory(null, 9, "Help");
		info.setItem(0, ItemModule.item("Links", "Discord and vote links.", Material.BOOK));
		info.setItem(1, ItemModule.item("Guide", "Helpful Guide", Material.WOODEN_SWORD));
		info.setItem(2, ItemModule.item("Stats", "Your statistics.", Material.INK_SAC));
		info.setItem(3, ItemModule.item("Players", "&f" + Bukkit.getServer().getOnlinePlayers().size() + " &7Players Online", Material.WITHER_SKELETON_SKULL));
		info.setItem(4, ItemModule.item("Rules", "Follow the Rules", Material.WITHER_SKELETON_SPAWN_EGG));
		return info;
	}
	
	private Inventory rules() {
		Inventory info = Bukkit.createInventory(null, 27, "Rules");
		info.setItem(0, ItemModule.item("World", "No Griefing\nNo Raiding\nNo Mods / Hacks\nNo 1x1 Towers\nRespect the Environment\nNo Lag Contraptions", Material.IRON_NUGGET));
		info.setItem(1, ItemModule.item("Chat", "Do not Bypass the Swear Filter"
				+ "\nNo Advertising\nNo Spamming\nDo not Ask for OP / Rank / Items\nNo Full Caps\nRespect All Players", Material.IRON_NUGGET));
		info.setItem(22, ItemModule.item("Back", "Go Back", Material.BLACK_STAINED_GLASS_PANE));
		return info;
	}

	private Inventory links() {
		Inventory info = Bukkit.createInventory(null, 27, "Links");
		info.setItem(0, ItemModule.item("Discord", "Chat hangout", Material.ARROW));
		info.setItem(1, ItemModule.item("Vote", "Help get the server noticed.", Material.JUNGLE_SAPLING));
		info.setItem(2, ItemModule.item("Donate", "Donate", Material.GOLD_NUGGET));
		info.setItem(22, ItemModule.item("Back", "Go Back", Material.BLACK_STAINED_GLASS_PANE));
		return info;
	}

	private Inventory usefulCommands() {
		Inventory info = Bukkit.createInventory(null, 27, "Commands");
		info.setItem(0, ItemModule.item("Spawn", "/spawn", Material.COMPASS));
		info.setItem(1, ItemModule.item("Home", "/sethome\n/delhome\n/home", Material.BLACK_BED));
		info.setItem(2, ItemModule.item("Tpa", "/tpa\n/tpahere\n/tpaccept\n/tpdeny", Material.WITHER_SKELETON_SPAWN_EGG));
		info.setItem(3, ItemModule.item("Back", "/back", Material.REDSTONE));
		info.setItem(22, ItemModule.item("Back", "Go Back", Material.BLACK_STAINED_GLASS_PANE));
		return info;
	}

	private Inventory mobGuide() {
		Inventory info = Bukkit.createInventory(null, 27, "Mob Guide");
		info.setItem(0, ItemModule.item("Slime", "Inflicts poison on touch.", Material.SLIME_SPAWN_EGG));
		info.setItem(1, ItemModule.item("Zombie", "Splits on death during the night.", Material.ZOMBIE_SPAWN_EGG));
		info.setItem(2, ItemModule.item("Husk", "Splits on death during the night.\nFaster than most mobs.",
				Material.HUSK_SPAWN_EGG));
		info.setItem(3,
				ItemModule.item("Spider", "Splits on death.\nFaster than most mobs.", Material.SPIDER_SPAWN_EGG));
		info.setItem(4,
				ItemModule.item("Phantom", "Nothing has changed with these fella's.", Material.PHANTOM_SPAWN_EGG));
		info.setItem(5, ItemModule.item("Wither Skeleton", "Insanely fast.", Material.WITHER_SKELETON_SPAWN_EGG));
		info.setItem(6, ItemModule.item("Wither", "Spawn in numerous amounts.", Material.WITHER_SKELETON_SPAWN_EGG));
		info.setItem(22, ItemModule.item("Back", "Go Back", Material.BLACK_STAINED_GLASS_PANE));
		return info;
	}

	private Inventory tutorial() {
		Inventory info = Bukkit.createInventory(null, 27, "Tutorial");
		info.setItem(0, ItemModule.item("Recipes", "Recipe Guide", Material.CRAFTING_TABLE));
		info.setItem(1, ItemModule.item("Mobs", "Mob Guide", Material.BOW));
		info.setItem(2, ItemModule.item("Commands", "Useful Commands", Material.COMMAND_BLOCK));
		info.setItem(3, ItemModule.item("Useful Info", "Very Helpful Info", Material.OAK_BOAT));
		info.setItem(22, ItemModule.item("Back", "Go Back", Material.BLACK_STAINED_GLASS_PANE));
		return info;
	}
	
	private Inventory usefulInfo() {
		Inventory info = Bukkit.createInventory(null, 27, "Useful Info");
		info.setItem(0, ItemModule.item("Tip #1", "Mobs drop corpses on death."
				+ "\nRight click them for loot.", Material.REDSTONE));
		info.setItem(1, ItemModule.item("Tip #2", "Mobs drop souls on death."
				+ "\nRight click them to absorb souls."
				+ "\nSouls give inventory space and hearts."
				+ "\nAll soul perks reset on death.", Material.FLINT));
		info.setItem(2, ItemModule.item("Tip #3", "The farther you venture out from spawn,"
				+ "\nthe harder it gets."
				+ "\nSome mobs split on death during the night."
				+ "\nSome mobs can jump over walls 3 blocks high."
				+ "\nOthers are just extremely fast.", Material.SUGAR));
		info.setItem(3, ItemModule.item("Tip #4", "Most blocks have gravity on place,"
				+ "\nexcept iron blocks.", Material.STICK));
		info.setItem(4, ItemModule.item("Tip #5", "When you plant seeds, the blocks adjacent"
				+ "\nto the crop have a set chance to"
				+ "\nbecome crops as well allowing farms"
				+ "\nto spread rapidly across the world.", Material.GUNPOWDER));
		info.setItem(5, ItemModule.item("Tip #6", "If you mine deep enough past the"
				+ "\nlava layer underground, you will"
				+ "\nfind a cave biome.", Material.INK_SAC));
		info.setItem(6, ItemModule.item("Tip #7", "Wearing a carved pumpkin will"
				+ "\nforce mobs to no longer target you.", Material.FEATHER));
		info.setItem(7, ItemModule.item("Tip #8", "If you ever run out of inventory"
				+ "\nspace, you can use your cats inventory.", Material.KELP));
		info.setItem(8, ItemModule.item("Tip #9", "If you ever forget how to craft"
				+ "\na recipe use the recipe guide found"
				+ "\nin /help > Guide > Recipes", Material.CLAY));
		info.setItem(22, ItemModule.item("Back", "Go Back", Material.BLACK_STAINED_GLASS_PANE));
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

	private Inventory stats(Player p) {
		Inventory info = Bukkit.createInventory(null, 36, "Stats");

		int deathTime = p.getStatistic(Statistic.TIME_SINCE_DEATH) / 20;
		String deathTimeString = formatTime(deathTime);

		int playTime = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 60;
		String playTimeString = formatTime(playTime);

		info.setItem(0, ItemModule.item("Last Death", deathTimeString, Material.ZOMBIE_HEAD));
		info.setItem(1, ItemModule.item("Deaths", "To be implemented.", Material.FLINT));
		info.setItem(2, ItemModule.item("Items Crafted", "" + getStatisticItemCount(p, Statistic.CRAFT_ITEM),
				Material.CRAFTING_TABLE));
		info.setItem(3, ItemModule.item("Mobs Killed", "" + p.getStatistic(Statistic.MOB_KILLS),
				Material.WITHER_SKELETON_SPAWN_EGG));
		info.setItem(4, ItemModule.item("Items Enchanted", "" + p.getStatistic(Statistic.ITEM_ENCHANTED),
				Material.ENCHANTING_TABLE));
		info.setItem(5,
				ItemModule.item("Items Broken", "" + getStatisticItemCount(p, Statistic.BREAK_ITEM), Material.STICK));
		info.setItem(6, ItemModule.item("Items Picked Up", "" + getStatisticItemCount(p, Statistic.PICKUP),
				Material.IRON_NUGGET));
		info.setItem(7, ItemModule.item("Items Dropped", "" + p.getStatistic(Statistic.DROP_COUNT), Material.STRING));
		info.setItem(8,
				ItemModule.item("Damage Dealt", "" + p.getStatistic(Statistic.DAMAGE_DEALT), Material.IRON_SWORD));
		info.setItem(9, ItemModule.item("Damage Taken", "" + p.getStatistic(Statistic.DAMAGE_TAKEN), Material.SHIELD));
		info.setItem(10, ItemModule.item("Animals Bred", "" + p.getStatistic(Statistic.ANIMALS_BRED),
				Material.CHICKEN_SPAWN_EGG));
		info.setItem(11, ItemModule.item("Chests Opened", "" + p.getStatistic(Statistic.CHEST_OPENED), Material.CHEST));
		info.setItem(12, ItemModule.item("Jumps", "" + p.getStatistic(Statistic.JUMP), Material.FEATHER));
		info.setItem(13,
				ItemModule.item("Server Rejoins", "" + p.getStatistic(Statistic.LEAVE_GAME), Material.OAK_SAPLING));
		info.setItem(14, ItemModule.item("Villager Trades", "" + p.getStatistic(Statistic.TRADED_WITH_VILLAGER),
				Material.VILLAGER_SPAWN_EGG));
		info.setItem(15, ItemModule.item("Time Played", playTimeString, Material.CLOCK));
		info.setItem(16, ItemModule.item("Distance Walked", p.getStatistic(Statistic.WALK_ONE_CM) / 100 + " Metres",
				Material.FEATHER));
		info.setItem(17, ItemModule.item("Distance Sprinted", p.getStatistic(Statistic.SPRINT_ONE_CM) / 100 + " Metres",
				Material.FEATHER));
		info.setItem(31, ItemModule.item("Back", "Go Back", Material.BLACK_STAINED_GLASS_PANE));
		return info;
	}

	private String formatTime(int seconds) {
		Date date = new Date((long) (seconds * 1000));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return "&f" + calendar.get(Calendar.HOUR) + "&7h, &f" + calendar.get(Calendar.MINUTE) + " &7m, &f"
				+ calendar.get(Calendar.SECOND) + " &7s";
	}

	@SuppressWarnings("deprecation")
	private Inventory players() {
		Inventory info = Bukkit.createInventory(null, 54, "Players");
		int i = 0;
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
			SkullMeta meta = (SkullMeta) skull.getItemMeta();
			meta.setOwningPlayer(p);
			meta.setDisplayName(TextModule.color("&r&f" + p.getName()));

			int deathTime = p.getStatistic(Statistic.TIME_SINCE_DEATH) / 20;
			String deathTimeString = formatTime(deathTime);

			int playTime = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 60;
			String playTimeString = formatTime(playTime);

			List<String> lore = new ArrayList<String>();
			lore.add(TextModule.color("&r&7Health: &f" + p.getHealth() / 2 + " &7/&f " + p.getMaxHealth() / 2));
			lore.add(TextModule.color("&7Items Crafted: &f" + getStatisticItemCount(p, Statistic.CRAFT_ITEM)));
			lore.add(TextModule.color("&7Mobs Killed: &f" + p.getStatistic(Statistic.MOB_KILLS)));
			lore.add(TextModule.color("&7Distance Walked: &f" + p.getStatistic(Statistic.WALK_ONE_CM) / 100 + " Metres"));
			lore.add(TextModule.color("&7Last Death: &f" + deathTimeString));
			lore.add(TextModule.color("&7Time Played: &f" + playTimeString));
			lore.add(TextModule.color("&7Rejoins: &f" + p.getStatistic(Statistic.LEAVE_GAME)));

			meta.setLore(lore);
			skull.setItemMeta(meta);

			info.setItem(i, skull);
			i++;
		}
		info.setItem(49, ItemModule.item("Back", "Go Back", Material.BLACK_STAINED_GLASS_PANE));
		return info;
	}

	private int getStatisticItemCount(Player p, Statistic stat) {
		int amount = 0;

		Iterator<Recipe> recipes = Bukkit.getServer().recipeIterator();
		while (recipes.hasNext()) {
			Recipe recipe = recipes.next();
			if (recipe != null && recipe.getResult() != null) {
				amount += p.getStatistic(stat, recipe.getResult().getType());
			}
		}

		return amount;
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
		case "rules":
			e.setCancelled(true);
			
			switch (slot) {
			case 22:
				p.openInventory(help());
				break;
			}
		case "players":
			e.setCancelled(true);

			switch (slot) {
			case 49:
				p.openInventory(help());
				break;
			}
			break;
		case "stats":
			e.setCancelled(true);

			switch (slot) {
			case 31:
				p.openInventory(help());
				break;
			}
			break;
		case "commands":
			e.setCancelled(true);

			switch (slot) {
			case 22:
				p.openInventory(tutorial());
				break;
			}
			break;
		case "links":
			e.setCancelled(true);

			switch (slot) {
			case 0:
				p.sendMessage("");
				p.sendMessage(TextModule.color("&7https://discord.gg/xhXeFhr"));
				p.sendMessage("");
				p.closeInventory();
				break;
			case 1:
				p.sendMessage("");

				p.sendMessage(TextModule.color("&fMinecraft-Server-List"));
				p.sendMessage(TextModule.color("&7https://minecraft-server-list.com/server/436060/vote/"));

				p.sendMessage(TextModule.color("&fMinecraft-MP"));
				p.sendMessage(TextModule.color("&7https://minecraft-mp.com/server/214716/vote/"));

				p.sendMessage(TextModule.color("&fPlanetMinecraft"));
				p.sendMessage(TextModule.color(
						"&7https://www.planetminecraft.com/server/1-13-2-peaceful-no-grief-no-pvp-survival-server/vote/"));

				p.sendMessage("");

				p.closeInventory();
				break;
			case 2:
				p.sendMessage("");
				p.sendMessage("http://valkmc.tebex.io");
				p.sendMessage("");
				p.closeInventory();
				break;
			case 22:
				p.openInventory(help());
				break;
			}
			break;
		case "help":
			e.setCancelled(true);

			switch (slot) {
			case 0:
				p.openInventory(links());
				break;
			case 1:
				p.openInventory(tutorial());
				break;
			case 2:
				p.openInventory(stats(p));
				break;
			case 3:
				p.openInventory(players());
				break;
			case 4:
				p.openInventory(rules());
				break;
			}
			break;
		case "mob guide":
			e.setCancelled(true);

			switch (slot) {
			case 22:
				p.openInventory(tutorial());
				break;
			}
			break;
		case "useful info":
			e.setCancelled(true);
			switch (slot) {
			case 22:
				p.openInventory(tutorial());
				break;
			}
			break;
		case "tutorial":
			e.setCancelled(true);

			switch (slot) {
			case 0:
				p.openInventory(recipeHome());
				break;
			case 1:
				p.openInventory(mobGuide());
				break;
			case 2:
				p.openInventory(usefulCommands());
				break;
			case 3:
				p.openInventory(usefulInfo());
				break;
			case 22:
				p.openInventory(help());
				break;
			}
			break;
		case "recipe home":
			e.setCancelled(true);

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
				p.openInventory(tutorial());
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	/*
	 * Recipe home navigation pages.
	 */
	@SuppressWarnings("deprecation")
	public void itemNavigation(InventoryClickEvent event, List<Inventory> navigation, Map<String, Integer> navTracker,
			List<Inventory> recipes, Map<String, Integer> recipeTracker) {
		Player p = (Player) event.getWhoClicked();
		String invName = event.getView().getTitle();

		boolean isAir = false;
		if (event.getCurrentItem() != null) {
			isAir = event.getCurrentItem().getType().equals(Material.AIR);
		}

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
}
