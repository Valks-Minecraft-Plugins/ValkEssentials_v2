package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invsee extends Cmd {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		if (!cmd("invsee", "essentials.invsee", 0)) return false;
		
		if (args.length < 1) return true;
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) return true;
		Player viewer = (Player) sender;
		viewer.openInventory(target.getInventory());
		return true;
	}
}
