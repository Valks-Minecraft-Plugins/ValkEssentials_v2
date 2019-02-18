package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.valkessentials.ValkEssentials;
import com.valkessentials.configs.PlayerFiles;

public class Back extends Cmd {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		if (!cmd("back", "none", 120)) return false;
		
		Player p = (Player) sender;
		
		PlayerFiles cm = PlayerFiles.getConfig(p);
		FileConfiguration config = cm.getConfig();
		
		if (!config.isSet("death_loc.world")) {
			sender.sendMessage(ValkEssentials.getPrefix() + "Seems like you didn't die yet.");
			return true;
		}
		
		Location loc = new Location(Bukkit.getWorld(config.getString("death_loc.world")), config.getInt("death_loc.x"), config.getInt("death_loc.y"), config.getInt("death_loc.z"));
		
		p.teleport(loc);
		
		return true;
	}
}
