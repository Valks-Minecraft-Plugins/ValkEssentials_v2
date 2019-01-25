package com.valkessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;

public class Fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("fly")) {
			if (!sender.hasPermission("essentials.fly")) {
				sender.sendMessage(ValkEssentials.getPrefix() + "You need essentials.fly to do that.");
				return true;
			}
			
			Player p = (Player) sender;
			
			if (p.isFlying()) {
				p.setFlying(false);
			} else {
				p.setFlying(true);
			}
		}
		return false;
	}

}
