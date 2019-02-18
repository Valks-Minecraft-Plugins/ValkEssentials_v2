package com.valkessentials.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import com.valkessentials.ValkEssentials;
import com.valkessentials.events.RankChangeEvent;

public class RankChangeEventListener implements Listener {
	@EventHandler
	private void rankChangeEvent(RankChangeEvent e) {
		PermissionAttachment p = e.getPlayer().addAttachment(ValkEssentials.main);
		
		toggleAllPerms(p, false);
		p.remove();
		
		switch (e.getRank()) {
		case "trial builder":
		case "builder":
			break;
		case "trial mod":
		case "mod":
			// Moderator
			setPerm(p, "essentials.butcher");
			setPerm(p, "essentials.mute");
			setPerm(p, "essentials.fly");
			setPerm(p, "essentials.color");
			setPerm(p, "essentials.gamemode");
			setPerm(p, "essentials.gamemode.spectator");
			setPerm(p, "essentials.gamemode.survival");
			setPerm(p, "essentials.invsee");
			setPerm(p, "essentials.kick");
			setPerm(p, "essentials.teleport");
			setPerm(p, "essentials.tpa");
			setPerm(p, "essentials.tpaccept");
			setPerm(p, "essentials.tpdeny");
			setPerm(p, "essentials.tpahere");
			setPerm(p, "minecraft.command.banlist");
			setPerm(p, "minecraft.command.clear");
			setPerm(p, "minecraft.command.kill");
			setPerm(p, "minecraft.command.toggledownfall");
			setPerm(p, "minecraft.command.weather");
			setPerm(p, "minecraft.command.time");
			break;
		case "trial admin":
		case "admin":
			// Moderator
			setPerm(p, "essentials.butcher");
			setPerm(p, "essentials.mute");
			setPerm(p, "essentials.fly");
			setPerm(p, "essentials.color");
			setPerm(p, "essentials.gamemode");
			setPerm(p, "essentials.gamemode.spectator");
			setPerm(p, "essentials.gamemode.survival");
			setPerm(p, "essentials.invsee");
			setPerm(p, "essentials.kick");
			setPerm(p, "essentials.teleport");
			setPerm(p, "essentials.tpa");
			setPerm(p, "essentials.tpaccept");
			setPerm(p, "essentials.tpdeny");
			setPerm(p, "essentials.tpahere");
			setPerm(p, "minecraft.command.banlist");
			setPerm(p, "minecraft.command.clear");
			setPerm(p, "minecraft.command.kill");
			setPerm(p, "minecraft.command.toggledownfall");
			setPerm(p, "minecraft.command.weather");
			setPerm(p, "minecraft.command.time");
			
			// Admin
			setPerm(p, "minecraft.command.ban");
			setPerm(p, "minecraft.command.ban-ip");
			setPerm(p, "minecraft.command.stop");
			setPerm(p, "minecraft.command.deop");
			setPerm(p, "minecraft.command.difficulty");
			setPerm(p, "minecraft.command.gamerule");
			setPerm(p, "minecraft.command.enchant");
			setPerm(p, "minecraft.command.give");
			setPerm(p, "minecraft.command.pardon");
			setPerm(p, "minecraft.command.pardon-ip");
			setPerm(p, "minecraft.command.save-all");
			setPerm(p, "minecraft.command.xp");
			setPerm(p, "essentials.gamemode.creative");
			setPerm(p, "essentials.rank");
			setPerm(p, "essentials.speed");
			break;
		case "trial dev":
		case "dev":
			break;
		case "donator":
			setPerm(p, "essentials.color");
			break;
		case "co-owner":
		case "owner":
			toggleAllPerms(p, true);
			break;
		default:
			setPerm(p, "essentials.tpa");
			setPerm(p, "essentials.tpaccept");
			setPerm(p, "essentials.tpdeny");
			setPerm(p, "essentials.tpahere");
			break;
		}
	}
	
	private void setPerm(PermissionAttachment attachment, String perm) {
		attachment.unsetPermission(perm);
		attachment.setPermission(perm, true);
	}
	
	private void toggleAllPerms(PermissionAttachment attachment, boolean enable) {
		String[] perms = {
				"essentials.mute",
				"essentials.fly",
				"essentials.color",
				"essentials.gamemode",
				"essentials.gamemode.spectator",
				"essentials.gamemode.survival",
				"essentials.invsee",
				"minecraft.command.kick",
				"minecraft.command.tp",
				"minecraft.command.teleport",
				"minecraft.command.banlist",
				"minecraft.command.clear",
				"minecraft.command.kill",
				"minecraft.command.toggledownfall",
				"minecraft.command.weather",
				"minecraft.command.time",
				"minecraft.command.ban",
				"minecraft.command.ban-ip",
				"minecraft.command.stop",
				"minecraft.command.deop",
				"minecraft.command.difficulty",
				"minecraft.command.gamerule",
				"minecraft.command.enchant",
				"minecraft.command.give",
				"minecraft.command.pardon",
				"minecraft.command.pardon-ip",
				"minecraft.command.save-all",
				"minecraft.command.xp",
				"essentials.gamemode.creative",
				"essentials.rank",
				"essentials.speed"
		};
		
		for (String perm : perms) {
			attachment.setPermission(perm, enable);
		}
	}
}
