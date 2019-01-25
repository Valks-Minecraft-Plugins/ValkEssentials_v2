package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;

public class Speed implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (cmd.getName().equalsIgnoreCase("speed")) {
			Player p = Bukkit.getPlayer(sender.getName());
			if (!sender.hasPermission("essentials.speed")) {
				sender.sendMessage(ValkEssentials.getPrefix() + "You need essentials.speed to do that.");
				return true;
			}
			
			if (args.length < 1) {
				p.sendMessage(ValkEssentials.getPrefix() + "Specify speed!");
				return true;
			}
			
			try {
				if (p.isFlying()) {
					p.setFlySpeed(Float.valueOf(args[0]));
					p.sendMessage(ValkEssentials.getPrefix() + "Set flyspeed to " + args[0]);
				} else {
					p.setWalkSpeed(Float.valueOf(args[0]));
					p.sendMessage(ValkEssentials.getPrefix() + "Set walkspeed to " + args[0]);
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			return true;
		}
		// TODO Auto-generated method stub
		return true;
	}

}
