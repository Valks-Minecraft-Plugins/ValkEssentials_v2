package com.valkessentials.msc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.server.TabCompleteEvent;

public class Msc implements Listener {
	@EventHandler
	private void tabComplete(TabCompleteEvent e) {
		if (e.getSender().isOp()) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	private void onPlayerTab(PlayerCommandSendEvent e) {
		if (e.getPlayer().isOp()) return;
		e.getCommands().clear();
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		if (e.getPlayer().isOp()) return;
		String[] allowedCmds = {"/help", "/pl", "/plugins", "/sethome", "/delhome", "/home", "/whisper"};
		for (String cmd : allowedCmds) {
			if (e.getMessage().equals(cmd)) {
				return;
			}
		}
		e.setCancelled(true);
	}
}
