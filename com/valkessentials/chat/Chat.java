package com.valkessentials.chat;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.valkessentials.ValkEssentials;
import com.valkessentials.configs.PlayerFiles;

public class Chat implements Listener {
	ValkEssentials plugin = null;
	public Chat() {
		plugin = JavaPlugin.getPlugin(ValkEssentials.class);
	}
	
	@EventHandler
	private boolean playerChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		PlayerFiles cm = PlayerFiles.getConfig(p);
		FileConfiguration playerConfig = cm.getConfig();
		Configuration config = plugin.getMsgConfig();
		
		if (playerConfig.getBoolean("muted") == true) {
			p.sendMessage(ValkEssentials.getPrefix() + "You're muted, you may not speak.");
			event.setCancelled(true);
			return true;
		}

		String formattedMessage = ChatColor.translateAlternateColorCodes('&', config.getString("messages.chat").replace("%player%", event.getPlayer().getDisplayName()).replace("%message%", event.getMessage()).replace("#color#", playerConfig.getString("color")).replaceAll("%", "%%"));

		String prefix = config.getString("messages.defaultrank");
		List<String> stringPrefixs = config.getStringList("messages.ranks");

		for (String element : stringPrefixs) {
			int divider = element.indexOf(':');
			if (divider != -1) {
				String rank = element.substring(0, divider);
				if (rank.toLowerCase().equals(playerConfig.getString("rank").toLowerCase())) {
					final int DIVIDER_SPACE = 1;
					final int SPACE = 1;
					prefix = element.substring(divider + DIVIDER_SPACE + SPACE);
				}
			}
		}

		String prefixColor = ChatColor.translateAlternateColorCodes('&', prefix);
		
		for (String blocked_word : config.getStringList("messages.blockedwords")) {
			formattedMessage = Pattern.compile(Pattern.quote(blocked_word), Pattern.CASE_INSENSITIVE).matcher(formattedMessage).replaceAll(Matcher.quoteReplacement(config.getString("messages.blockedreplace")));
		}
		
		event.setFormat(prefixColor + formattedMessage);
		
		return true;
	}
}
