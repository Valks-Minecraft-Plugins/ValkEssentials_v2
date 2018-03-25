package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;
import com.valkessentials.configs.PlayerFiles;

public class Mute implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("mute")) {
			if (!sender.isOp()) {
				sender.sendMessage(ValkEssentials.getPrefix() + "You're not op.");
				return true;
			}
			
			if (args.length < 1) {
				sender.sendMessage(ValkEssentials.getPrefix() + "Usage: /mute <player>");
				return true;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(ValkEssentials.getPrefix() + args[0] + " is not online.");
				return true;
			}
			
			PlayerFiles cm = PlayerFiles.getConfig(target);
			FileConfiguration config = cm.getConfig();
			if (config.getBoolean("muted") == false){
				config.set("muted", true);
				sender.sendMessage(ValkEssentials.getPrefix() + "Muted " + args[0] + ".");
				target.sendMessage(ValkEssentials.getPrefix() + "You were muted by " + sender.getName() + ".");
			} else {
				config.set("muted", false);
				sender.sendMessage(ValkEssentials.getPrefix() + "Unmuted " + args[0] + ".");
				target.sendMessage(ValkEssentials.getPrefix() + "You were unmuted by " + sender.getName() + ".");
			}
			return true;
		}
		return false;
	}

}
