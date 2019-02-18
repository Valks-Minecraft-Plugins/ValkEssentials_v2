package com.valkessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.valkessentials.ValkEssentials;
import com.valkutils.modules.TextModule;

public class Tpa extends Cmd {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		super.onCommand(sender, command, label, args);
		
		Player player = (Player) sender;
		
		if (cmd("tpa", "essentials.tpa", 120)) {
			if (ValkEssentials.request.containsValue(player.getUniqueId())) {
				Player target = Bukkit.getPlayer(ValkEssentials.request.get(player.getUniqueId()));
				if (target == null) {
					sender.sendMessage("Seems like the target was null?");
				} else {
					sender.sendMessage(TextModule.color("&7A teleport request is already active with &f" + target.getName()));
				}
				
				return false;
			}
			
			if (args.length < 1) {
				sender.sendMessage(TextModule.color("&7You need to specify a online player."));
				return false;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if (target == null) {
				sender.sendMessage(TextModule.color("&f" + args[0] + " &7is a Invalid player."));
				return false;
			}
			
			if (target == player) {
				sender.sendMessage(TextModule.color("&7You cannot teleport to yourself."));
				return false;
			}
			
			player.sendMessage(TextModule.color("&7Sent a teleport request to &f" + target.getName() + "&7."));
			target.sendMessage(TextModule.color("&7Recieved a teleport request to teleport to you from &f" + player.getName() + "&7, do you accept?"));
			
			ValkEssentials.request.put(target.getUniqueId(), player.getUniqueId());
			
			new BukkitRunnable() {
				@Override
				public void run() {
					if (ValkEssentials.request.containsKey(target.getUniqueId())) {
						ValkEssentials.request.remove(target.getUniqueId());
						player.sendMessage(TextModule.color("&7Teleport request to &f" + target.getName() + " &7timed out."));
						target.sendMessage(TextModule.color("&7Teleport request from &f" + player.getName() + " &7timed out."));
					}
				}
			}.runTaskLater(ValkEssentials.main, 20 * 30);
			
			return true;
		}
		
		if (cmd("tpahere", "essentials.tpahere", 120)) {
			if (ValkEssentials.request.containsValue(player.getUniqueId())) {
				Player target = Bukkit.getPlayer(ValkEssentials.request.get(player.getUniqueId()));
				if (target == null) {
					sender.sendMessage("Seems like the target was null?");
				} else {
					sender.sendMessage(TextModule.color("&7A teleport request is already active with &f" + target.getName()));
				}
				
				return false;
			}
			
			if (args.length < 1) {
				sender.sendMessage(TextModule.color("&7You need to specify a online player."));
				return false;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if (target == null) {
				sender.sendMessage(TextModule.color("&f" + args[0] + " &7is a Invalid player."));
				return false;
			}
			
			if (target == player) {
				sender.sendMessage(TextModule.color("&7You cannot request to teleport to yourself."));
				return false;
			}
			
			player.sendMessage(TextModule.color("&7Sent a teleport request to &f" + target.getName() + "&7."));
			target.sendMessage(TextModule.color("&7Recieved a teleport request to teleport to them from &f" + player.getName() + "&7, do you accept?"));
			
			ValkEssentials.request.put(player.getUniqueId(), target.getUniqueId());
			
			new BukkitRunnable() {
				@Override
				public void run() {
					if (ValkEssentials.request.containsKey(player.getUniqueId())) {
						ValkEssentials.request.remove(player.getUniqueId());
						player.sendMessage(TextModule.color("&7Teleport request to &f" + target.getName() + " &7timed out."));
						target.sendMessage(TextModule.color("&7Teleport request from &f" + player.getName() + " &7timed out."));
					}
				}
			}.runTaskLater(ValkEssentials.main, 20 * 30);
			
			return true;
		}
		
		if (cmd("tpaccept", "essentials.tpaccept", 0)) {
			if (!ValkEssentials.request.containsKey(player.getUniqueId())) {
				player.sendMessage(TextModule.color("&7Looks like there is no teleport request active for you."));
				return false;
			}
			
			Player requester = Bukkit.getPlayer(ValkEssentials.request.get(player.getUniqueId()));
			ValkEssentials.request.remove(player.getUniqueId());
			
			requester.teleport(player);
			requester.sendMessage(TextModule.color("&7Teleporting you to &f" + player.getName() + "&7."));
			player.sendMessage(TextModule.color("&7Teleport request accepted."));
			
			return true;
		}
		
		if (cmd("tpdeny", "essentials.tpdeny", 0)) {
			if (!ValkEssentials.request.containsKey(player.getUniqueId())) {
				player.sendMessage(TextModule.color("&7Looks like there is no teleport request active for you."));
				return false;
			}
			
			Player requester = Bukkit.getPlayer(ValkEssentials.request.get(player.getUniqueId()));
			
			ValkEssentials.request.remove(player.getUniqueId());
			
			requester.sendMessage(TextModule.color("&f" + player.getName() + " &7denied your teleport request."));
			player.sendMessage(TextModule.color("&7You denied &f" + requester.getName() + " &7teleport request."));
			
			return true;
		}
		
		return true;
	}
}
