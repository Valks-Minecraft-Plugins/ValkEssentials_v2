package com.valkessentials.msc;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.server.TabCompleteEvent;

import com.valkessentials.configs.PlayerFiles;

public class Msc implements Listener {
	private boolean isStaff(Player player) {
		PlayerFiles cm = PlayerFiles.getConfig(player);
		FileConfiguration config = cm.getConfig();
		if (!config.isSet("rank")) return false;
		String rank = config.getString("rank").toLowerCase();
		return player.isOp() || !rank.equals("default");
	}
	@EventHandler
	private void tabComplete(TabCompleteEvent e) {
		if (e.getSender() instanceof ConsoleCommandSender) return;
		Player player = (Player) e.getSender();
		if (isStaff(player)) return;
		
		e.setCancelled(true);
	}
	
	@EventHandler
	private void onPlayerTab(PlayerCommandSendEvent e) {
		if (isStaff(e.getPlayer())) return;
		e.getCommands().clear();
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		if (isStaff(e.getPlayer())) return;
		String[] allowedCmds = {"/help", "/spawn", "/pl", "/plugins", "/sethome", "/delhome", "/home", "/whisper", "/tpa", "/tpahere", "/tpdeny", "/tpaccept", "/back", "/w", "/tell", "/msg"};
		for (String cmd : allowedCmds) {
			if (e.getMessage().startsWith(cmd)) {
				return;
			}
		}
		e.setCancelled(true);
	}
}
