package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;
import com.valkessentials.configs.PlayerFiles;

public class Home extends Cmd {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		
		if (cmd("delhome", "none", 0)) {
			Player target = Bukkit.getPlayer(sender.getName());
			PlayerFiles cm = PlayerFiles.getConfig(target);
			FileConfiguration config = cm.getConfig();
			config.set("home.x", null);
			config.set("home.y", null);
			config.set("home.z", null);
			config.set("home.world", null);
			cm.saveConfig();
			sender.sendMessage(ValkEssentials.getPrefix() + "Home removed.");
			return true;
		}
		
		if (cmd("sethome", "none", 0)) {
			Player target = Bukkit.getPlayer(sender.getName());
			PlayerFiles cm = PlayerFiles.getConfig(target);
			FileConfiguration config = cm.getConfig();
			Location loc = target.getLocation();
			config.set("home.x", loc.getBlockX());
			config.set("home.y", loc.getBlockY());
			config.set("home.z", loc.getBlockZ());
			config.set("home.world", target.getWorld().getName());
			cm.saveConfig();
			sender.sendMessage(ValkEssentials.getPrefix() + "Home set.");
			
			return true;
		}
		
		if (cmd("home", "none", 120)) {
			Player target = Bukkit.getPlayer(sender.getName());

			if (sender instanceof ConsoleCommandSender) {
				sender.sendMessage(ValkEssentials.getPrefix() + "The console should not have a bed to teleport to.. lol..");
				return true;
			}

			PlayerFiles cm = PlayerFiles.getConfig(target);
			FileConfiguration config = cm.getConfig();
			Location bed = target.getBedSpawnLocation();
			
			if (config.isSet("home.world")) {
				target.sendMessage(ValkEssentials.getPrefix() + "Teleporting to your home set in config.");
				String world = config.getString("home.world");
				int x = config.getInt("home.x");
				int y = config.getInt("home.y");
				int z = config.getInt("home.z");
				Location loc = new Location(Bukkit.getWorld(world), x, y, z);
				target.teleport(loc);
			} else if (bed != null) {
				target.sendMessage(ValkEssentials.getPrefix() + "Teleporting you to your bed that you created.");
				target.teleport(bed);
			} else {
				target.sendMessage(ValkEssentials.getPrefix() + "Make a bed or set a home with /sethome.");
			}
			
			return true;
		}
		
		
		return true;
	}

}
