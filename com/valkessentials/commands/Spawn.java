package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;

public class Spawn extends Cmd {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		if (!cmd("spawn", "none", 120)) return false;

		Player p = (Player) sender;
		World w = Bukkit.getWorld("world");
		p.teleport(w.getSpawnLocation());
		p.sendMessage(ValkEssentials.getPrefix() + "Teleporting you to spawn..");
		
		return true;
	}

}
