package com.valkessentials;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.valkessentials.chat.Broadcasts;
import com.valkessentials.chat.Chat;
import com.valkessentials.commands.Color;
import com.valkessentials.commands.Help;
import com.valkessentials.commands.Home;
import com.valkessentials.commands.Kick;
import com.valkessentials.commands.Mute;
import com.valkessentials.commands.Rank;
import com.valkessentials.commands.Speed;
import com.valkessentials.commands.Teleport;
import com.valkessentials.commands.Whisper;
import com.valkessentials.configs.LoadPlayerFiles;

public class ValkEssentials extends JavaPlugin {
	public static ValkEssentials main;
	private static String name = "";
	
	File msgConfigFile = new File(getDataFolder(), "messages.yml");
	FileConfiguration msgConfig = YamlConfiguration.loadConfiguration(msgConfigFile);
	
	public ValkEssentials() {
		name = getDescription().getName();
	}

	@Override
	public void onEnable() {
		setConfigMsgValues();
		saveMsgConfig();
		
		registerCommands();
		registerEvents();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new LoadPlayerFiles(), this);
		pm.registerEvents(new Chat(), this);
		pm.registerEvents(new Broadcasts(), this);
		pm.registerEvents(new Help(), this);
	}
	
	private void registerCommands() {
		getCommand("help").setExecutor(new Help());
		
		getCommand("home").setExecutor(new Home());
		getCommand("sethome").setExecutor(new Home());
		getCommand("delhome").setExecutor(new Home());
		
		getCommand("tpa").setExecutor(new Teleport());
		getCommand("tpaccept").setExecutor(new Teleport());
		getCommand("tpdeny").setExecutor(new Teleport());
		
		getCommand("kick").setExecutor(new Kick());
		getCommand("mute").setExecutor(new Mute());
		getCommand("rank").setExecutor(new Rank());
		getCommand("speed").setExecutor(new Speed());
		getCommand("color").setExecutor(new Color());
		
		getCommand("whisper").setExecutor(new Whisper());
	}

	public static String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', "&8{&f" + name + "&8} &7");
	}
	
	public Configuration getMsgConfig() {
		return msgConfig;
	}
	
	public void saveMsgConfig() {
		try {
			msgConfig.save(msgConfigFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setConfigMsgValues() {
		if (!msgConfig.isSet("messages.chat")) {
			msgConfig.set("messages.chat", "&8%player%&8: &7#color#%message%");
		}

		if (!msgConfig.isSet("messages.blockedwords")) {
			List<String> msgs = Arrays.asList("fuck", "nigger", "cunt", "cunts", "bitch", "whore", "slut", "motherfucker",
					"fucker", "blowjob", "dick", "kunt", "faggot", "niglet", "prick");
			msgConfig.set("messages.blockedwords", msgs);
		}
		if (!msgConfig.isSet("messages.blockedreplace")) {
			msgConfig.set("messages.blockedreplace", "meeowww");
		}

		if (!msgConfig.isSet("messages.ranks")) {
			List<String> msgs = Arrays.asList("Mod: &8{&2Moderator&8} ", "Admin: &8{&cAdmin&8} ", "Owner: &8{&fOwner&8} ");
			msgConfig.set("messages.ranks", msgs);
		}
		if (!msgConfig.isSet("messages.defaultrank")) {
			msgConfig.set("messages.defaultrank", "&8{&7User&8} ");
		}

		if (!msgConfig.isSet("messages.join")) {
			List<String> msgs = Arrays.asList("&8{&b+&8} &7A wild &3%player% &7appeared!",
					"&8{&b+&8} &7&3%player% &7has been summoned!", "&8{&b+&8} &7&3%player% &7came out of no where!",
					"&8{&b+&8} &7A wild &3%player% &7appeared! Say hi!");
			msgConfig.set("messages.join", msgs);
		}

		if (!msgConfig.isSet("messages.welcome")) {
			List<String> msgs = Arrays.asList("&8{&b+&8} &dIts &3%player% &dfirst time here! Welcome!!",
					"&8{&b+&8} &d&3%player% &dfell from the sky! Its their first time here! Welcome!",
					"&8{&b+&8} &d&3%player% &ddiscovered catlandia! +69XP Its their first time here! Welcome!");
			msgConfig.set("messages.welcome", msgs);
		}

		if (!msgConfig.isSet("messages.leave")) {
			List<String> msgs = Arrays.asList("&8{&b-&8} &3%player% &7vanished into thin air",
					"&8{&b-&8} &3%player% &7dissapeared", "&8{&b-&8} &3%player% &7thought cats were too much for them",
					"&8{&b-&8} &3%player% &7left their cat behind",
					"&8{&b-&8} &3%player% &7learned that kittens are really tough", "&8{&b-&8} &3%player% &7divorced their cats",
					"&8{&b-&8} &3%player% &7ran away from the cats");
			msgConfig.set("messages.leave", msgs);
		}

		if (!msgConfig.isSet("messages.kick")) {
			List<String> msgs = Arrays.asList("&8{&b-&8} &3%player% &7was kicked");
			msgConfig.set("messages.kick", msgs);
		}

		if (!msgConfig.isSet("messages.death")) {
			List<String> msgs = Arrays.asList("&3%player% &7got devoured by a kitten", "&3%player% &7had a date with a cat",
					"&3%player% &7thought they were a cat", "&3%player% &7tried to tame a kitten",
					"&3%player%&7's limbs were smashed",
					"&3%player% &7learned to keep their distance from kittens and cats alike", "&3%player% &7fed a cat",
					"&3%player% &7got a cat as a pet", "&3%player% &7turned into cat food", "&3%player% &7thought cats could fly",
					"&3%player% &7turned into a cat", "&3%player% &7got married to a cat", "&3%player% &7got candy from a cat",
					"&3%player% &7became a kittens slave", "&3%player% &7made a kitten really mad.",
					"&3%player% &7was scratched to death by cute kittens.");
			msgConfig.set("messages.death", msgs);
		}
	}
}
