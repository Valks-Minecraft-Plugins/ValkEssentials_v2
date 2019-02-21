package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Vanish extends Cmd {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		if (!cmd("vanish", "essentials.vanish", 0))
			return false;

		Player p = Bukkit.getPlayer(sender.getName());

		if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400000, 100));
			p.setCanPickupItems(false);
			p.sendMessage("You're now in vanished mode.");
		} else {
			p.removePotionEffect(PotionEffectType.INVISIBILITY);
			p.setCanPickupItems(true);
			p.sendMessage("You're no longer in vanish mode.");
		}
		return true;
	}
}
