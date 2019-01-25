package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;

public class Kick implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kick")) {
			Player p = Bukkit.getPlayer(sender.getName());
			if (!sender.hasPermission("essentials.kick")) {
				sender.sendMessage(ValkEssentials.getPrefix() + "You need essentials.kick to do that.");
				return true;
			}
			
			if (args.length < 1) {
				p.sendMessage(ValkEssentials.getPrefix() + "Specify player name to kick.");
				return true;
			}
			
			Player target = Bukkit.getPlayer(args[0].toLowerCase());
			if (target == null) {
				sender.sendMessage(ValkEssentials.getPrefix() + args[0] + " is not online.");
				return true;
			}
			
			target.kickPlayer("Kicked");
			
			return true;
		}
		// TODO Auto-generated method stub
		return true;
	}

}
