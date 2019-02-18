package com.valkessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.valkessentials.utils.Cooldown;
import com.valkutils.modules.TextModule;

public class Cmd implements CommandExecutor {
	private CommandSender sender;
	private Command command;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		this.sender = sender;
		this.command = command;
		return true;
	}

	public boolean cmd(String senderCommand, String permission, int cooldownInSeconds) {
		if (!command.getName().equalsIgnoreCase(senderCommand))	return false;
		if (!(sender instanceof ConsoleCommandSender)) {
			Player player = (Player) sender;
			if (!sender.hasPermission(permission) && !permission.equalsIgnoreCase("none")) return false;
			if (cooldownInSeconds != 0) {
				if (Cooldown.isInCooldown(player.getUniqueId()) && !sender.isOp()) {
					sender.sendMessage(TextModule.color("&7Please wait &f" + Cooldown.getTimeLeft(player.getUniqueId()) + " &7seconds before using that command again."));
					return false;
				} else {
					Cooldown cooldown = new Cooldown(player.getUniqueId(), cooldownInSeconds);
					cooldown.start();
				}
			}
		}
		return true;
	}
}
