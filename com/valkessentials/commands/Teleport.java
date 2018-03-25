package com.valkessentials.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.valkessentials.ValkEssentials;

import net.md_5.bungee.api.ChatColor;

public class Teleport implements CommandExecutor {
	Map<String, Long> tpaCooldown = new HashMap<String, Long>();
	Map<String, String> currentRequest = new HashMap<String, String>();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player) sender;
		}

		if (command.getName().equalsIgnoreCase("tpa")) {
			if (!(p == null)) {

				int cooldown = 60;
				if (tpaCooldown.containsKey(p.getName())) {
					long diff = (System.currentTimeMillis() - tpaCooldown.get(sender.getName())) / 1000;
					if (diff < cooldown) {
						p.sendMessage(
								ValkEssentials.getPrefix() + "Please wait " + cooldown + " seconds between teleport requests.");
						return true;
					}
				}

				if (args.length > 0) {
					final Player target = Bukkit.getServer().getPlayer(args[0]);
					long keepAlive = 30 * 20;

					if (target == null) {
						sender.sendMessage(ValkEssentials.getPrefix() + args[0] + " is not online.");
						return true;
					}

					if (target == p) {
						sender.sendMessage(ValkEssentials.getPrefix() + "You can't teleport to yourself!");
						return true;
					}

					sendRequest(p, target);

					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
						public void run() {
							killRequest(target.getName());
						}
					}, keepAlive);

					tpaCooldown.put(p.getName(), System.currentTimeMillis());
				} else {
					p.sendMessage(ValkEssentials.getPrefix() + "/tpa <player>");
				}
			} else {
				sender.sendMessage(ValkEssentials.getPrefix() + "Console can't teleport to players.");
				return true;
			}
			return true;
		}

		if (command.getName().equalsIgnoreCase("tpaccept")) {
			if (!(p == null)) {
				if (currentRequest.containsKey(p.getName())) {

					Player heIsGoingOutOnADate = Bukkit.getServer().getPlayer(currentRequest.get(p.getName()));
					currentRequest.remove(p.getName());

					if (!(heIsGoingOutOnADate == null)) {
						heIsGoingOutOnADate.teleport(p);
						p.sendMessage(ValkEssentials.getPrefix() + "Teleporting...");
						heIsGoingOutOnADate.sendMessage(ChatColor.GRAY + "Teleporting...");
					} else {
						sender.sendMessage(ValkEssentials.getPrefix()
								+ "It appears that the person trying to teleport to you doesn't exist anymore. WHOA!");
						return true;
					}
				} else {
					sender.sendMessage(ValkEssentials.getPrefix()
							+ "It doesn't appear that there are any current tp requests. Maybe it timed out?");
					return true;
				}
			} else {
				sender.sendMessage(ValkEssentials.getPrefix() + "The console can't accept teleport requests, silly!");
				return true;
			}
			return true;
		}

		if (command.getName().equalsIgnoreCase("tpdeny")) {
			if (!(p == null)) {
				if (currentRequest.containsKey(p.getName())) {
					Player poorRejectedGuy = Bukkit.getServer().getPlayer(currentRequest.get(p.getName()));
					currentRequest.remove(p.getName());

					if (!(poorRejectedGuy == null)) {
						poorRejectedGuy
								.sendMessage(ValkEssentials.getPrefix() + p.getName() + " rejected your teleport request! :(");
						p.sendMessage(ValkEssentials.getPrefix() + poorRejectedGuy.getName() + " was rejected!");
						return true;
					}
				} else {
					sender.sendMessage(ValkEssentials.getPrefix()
							+ "It doesn't appear that there are any current tp requests. Maybe it timed out?");
					return true;
				}
			} else {
				sender.sendMessage(ValkEssentials.getPrefix() + "The console can't deny teleport requests, silly!");
				return true;
			}
			return true;
		}
		return false;
	}

	public boolean killRequest(String key) {
		if (currentRequest.containsKey(key)) {
			Player loser = Bukkit.getServer().getPlayer(currentRequest.get(key));
			if (!(loser == null)) {
				loser.sendMessage(ValkEssentials.getPrefix() + "Your teleport request timed out.");
			}

			currentRequest.remove(key);

			return true;
		} else {
			return false;
		}
	}

	public void sendRequest(Player sender, Player recipient) {
		sender.sendMessage(ValkEssentials.getPrefix() + "Sending a teleport request to " + recipient.getName() + ".");

		String sendtpaccept = "";
		String sendtpdeny = "";

		sendtpaccept = " To accept the teleport request, type " + ChatColor.RED + "/tpaccept" + ChatColor.DARK_GRAY + ".";

		sendtpdeny = " To deny the teleport request, type " + ChatColor.RED + "/tpdeny" + ChatColor.DARK_GRAY + ".";

		recipient.sendMessage(ValkEssentials.getPrefix() + sender.getName() + ChatColor.DARK_GRAY
				+ " has sent a request to teleport to you." + sendtpaccept + sendtpdeny);
		currentRequest.put(recipient.getName(), sender.getName());
	}
}
