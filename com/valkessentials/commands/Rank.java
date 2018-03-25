package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;
import com.valkessentials.configs.PlayerFiles;

public class Rank implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("rank")) {
			if (!sender.isOp()) {
				sender.sendMessage(ValkEssentials.getPrefix() + "You're not op.");
				return true;
			}

			if (args.length == 0) {
				sender.sendMessage(ValkEssentials.getPrefix() + "Specify at least one argument.");
				return true;
			}

			if (args.length < 2) {
				sender.sendMessage(ValkEssentials.getPrefix() + "/rank <player> <rank>");
				return true;
			}

			Player target = Bukkit.getServer().getPlayer(args[0]);
			
			if (target == null) {
				sender.sendMessage(ValkEssentials.getPrefix() + args[0] + " is not online.");
				return true;
			}
			
			PlayerFiles cm = PlayerFiles.getConfig(target);
			FileConfiguration config = cm.getConfig();
			config.set("rank", args[1]);
			cm.saveConfig();
			sender.sendMessage(ValkEssentials.getPrefix() + "Set rank " + args[1] + " for " + target.getName());
			target.sendMessage(ValkEssentials.getPrefix() + "Your rank was updated to " + args[1] + " by " + sender.getName());
			return true;
		}
		return false;
	}

}
