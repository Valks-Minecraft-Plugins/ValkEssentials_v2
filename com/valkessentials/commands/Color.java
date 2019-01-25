package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;
import com.valkessentials.configs.PlayerFiles;

public class Color implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("color")) {
			if (!sender.hasPermission("essentials.color")) {
				sender.sendMessage(ValkEssentials.getPrefix() + "You need essentials.color to do that.");
				return true;
			}
			
			if (args.length == 0) {
				sender.sendMessage(ValkEssentials.getPrefix() + "Specify at least one argument.");
				return true;
			}

			if (args.length >= 2) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(ValkEssentials.getPrefix() + args[0] + " is not online.");
					return true;
				}
				if (!args[1].contains("&")) {
					sender.sendMessage(ValkEssentials.getPrefix() + "Enter a valid color, ex. &2");
					return true;
				}
				PlayerFiles cm = PlayerFiles.getConfig(target);
				FileConfiguration config = cm.getConfig();
				config.set("color", args[1]);
				cm.saveConfig();
				sender.sendMessage(ValkEssentials.getPrefix() + "Set color " + args[1] + " for " + target.getName());
				target.sendMessage(ValkEssentials.getPrefix() + "Your color was changed to " + args[1] + " by " + sender.getName());
				return true;
			} else {
				Player target = Bukkit.getServer().getPlayer(sender.getName());
				if (sender instanceof ConsoleCommandSender) {
					sender.sendMessage(ValkEssentials.getPrefix() + "You can't give the console color chat.");
					return true;
				}
				
				if (!args[0].contains("&")) {
					sender.sendMessage(ValkEssentials.getPrefix() + "Enter a valid color, ex. &2");
					return true;
				}
				PlayerFiles cm = PlayerFiles.getConfig(target);
				FileConfiguration config = cm.getConfig();
				config.set("color", args[0]);
				cm.saveConfig();
				sender.sendMessage(ValkEssentials.getPrefix() + "Your color was changed to " + args[0]);
				return true;
			}
		}

		return false;
	}
}
