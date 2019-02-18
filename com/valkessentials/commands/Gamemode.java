package com.valkessentials.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode extends Cmd {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		if (!cmd("gamemode", "essentials.gamemode", 0)) return false;
		
		if (args.length < 1) {
			sender.sendMessage("Specify args");
			return true;
		}

		Player p = (Player) sender;

		switch (args[0].toLowerCase()) {
		case "survival":
			if (sender.hasPermission("essentials.gamemode.survival"))
				p.setGameMode(GameMode.SURVIVAL);
			else
				p.sendMessage("No perms.");
			return true;
		case "creative":
			if (sender.hasPermission("essentials.gamemode.creative"))
				p.setGameMode(GameMode.CREATIVE);
			else
				p.sendMessage("No perms.");
			return true;
		case "spectator":
			if (sender.hasPermission("essentials.gamemode.spectator"))
				p.setGameMode(GameMode.SPECTATOR);
			else
				p.sendMessage("No perms.");
			return true;
		case "adventure":
			if (sender.hasPermission("essentials.gamemode.adventure"))
				p.setGameMode(GameMode.ADVENTURE);
			else
				p.sendMessage("No perms.");
			return true;
		default:
			sender.sendMessage("Invalid gamemode.");
			return true;
		}
	}

}
