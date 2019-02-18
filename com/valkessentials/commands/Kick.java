package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;

public class Kick extends Cmd {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		if (!cmd("kick", "essentials.kick", 0)) return false;
		
		Player p = Bukkit.getPlayer(sender.getName());
		
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

}
