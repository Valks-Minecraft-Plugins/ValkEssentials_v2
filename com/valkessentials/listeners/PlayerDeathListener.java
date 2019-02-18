package com.valkessentials.listeners;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import com.valkessentials.configs.PlayerFiles;

public class PlayerDeathListener implements Listener {
	@EventHandler
	private void playerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if ((p.getHealth() - e.getDamage()) <= 0) { // Player died.
				PlayerFiles cm = PlayerFiles.getConfig(p);
				FileConfiguration config = cm.getConfig();
				
				Location loc = e.getEntity().getLocation();
				
				config.set("death_loc.world", loc.getWorld().getName());
				config.set("death_loc.x", loc.getBlockX());
				config.set("death_loc.y", loc.getBlockY());
				config.set("death_loc.z", loc.getBlockZ());
				
				cm.saveConfig();
			}
		}
	}
}
