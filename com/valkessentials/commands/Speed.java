package com.valkessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;

public class Speed extends Cmd {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		if (!cmd("speed", "essentials.speed", 0)) return false;
		
		Player p = (Player) sender;
		
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

}
