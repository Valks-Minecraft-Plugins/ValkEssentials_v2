package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Butcher extends Cmd {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		if (!cmd("butcher", "essentials.butcher", 0)) return false;
		
		Player p = Bukkit.getPlayer(sender.getName());
		World w = p.getWorld();
		for (LivingEntity entity : w.getLivingEntities()) {
			if (isMonster(entity)) {
				entity.remove();
			}
		}
		
		return true;
	}
	
	private boolean isMonster(LivingEntity entity) {
		switch (entity.getType()) {
		case BAT:
		case BLAZE:
		case CAVE_SPIDER:
		case CREEPER:
		case ELDER_GUARDIAN:
		case ENDERMAN:
		case ENDERMITE:
		case EVOKER:
		case GHAST:
		case ENDER_DRAGON:
		case GIANT:
		case GUARDIAN:
		case HUSK:
		case MAGMA_CUBE:
		case PHANTOM:
		case PIG_ZOMBIE:
		case SHULKER:
		case STRAY:
		case VEX:
		case VINDICATOR:
		case WITHER_SKELETON:
		case ZOMBIE:
		case SKELETON:
		case SPIDER:
		case WITCH:
			return true;
		default:
			return false;
		}
	}
}
