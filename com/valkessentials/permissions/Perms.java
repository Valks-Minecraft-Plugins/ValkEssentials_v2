package com.valkessentials.permissions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.valkessentials.configs.PlayerFiles;
import com.valkessentials.events.RankChangeEvent;

public class Perms implements Listener {
	@EventHandler
	private void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		PlayerFiles cm = PlayerFiles.getConfig(player);
		FileConfiguration config = cm.getConfig();
		String rank = config.getString("rank").toLowerCase();
		Bukkit.getPluginManager().callEvent(new RankChangeEvent(player, rank));
	}
}
