package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;

public class Whisper implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("whisper")) {	
			if (args.length < 2 || args.length == 0) {
				sender.sendMessage(ValkEssentials.getPrefix() + "Usage: /whisper <player> <message>");
				return true;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if (target == null) {
				sender.sendMessage(ValkEssentials.getPrefix() + args[0] + " is not online.");
				return true;
			}
			
			if (target == sender) {
				sender.sendMessage(ValkEssentials.getPrefix() + "You can't whisper to yourself.");
				return true;
			}
			
			StringBuilder strBuilder = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				if (i != 0) {
					strBuilder.append(args[i] + " ");
				}
			}
			String str = strBuilder.toString();
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&o" + sender.getName() + " &8&o>> &a&o" + target.getName() + "&8&o: &2&o" + str));
			target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&o" + sender.getName() + " &8&o>> &a&o" + "YOU" + "&8&o: &2&o" + str));
			return true;
		}
		return false;
	}

}
