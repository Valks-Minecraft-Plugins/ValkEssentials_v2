package com.valkessentials;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.valkessentials.chat.Broadcasts;
import com.valkessentials.chat.Chat;
import com.valkessentials.commands.Back;
import com.valkessentials.commands.Butcher;
import com.valkessentials.commands.Color;
import com.valkessentials.commands.Fly;
import com.valkessentials.commands.Gamemode;
import com.valkessentials.commands.Help;
import com.valkessentials.commands.Home;
import com.valkessentials.commands.Invsee;
import com.valkessentials.commands.Kick;
import com.valkessentials.commands.Mute;
import com.valkessentials.commands.Rank;
import com.valkessentials.commands.Spawn;
import com.valkessentials.commands.Speed;
import com.valkessentials.commands.Tpa;
import com.valkessentials.commands.Whisper;
import com.valkessentials.configs.LoadPlayerFiles;
import com.valkessentials.listeners.PlayerDeathListener;
import com.valkessentials.listeners.RankChangeEventListener;
import com.valkessentials.msc.Msc;
import com.valkessentials.permissions.Perms;

public class ValkEssentials extends JavaPlugin {
	public static ValkEssentials main;
	private static String name = "";

	File msgConfigFile = new File(getDataFolder(), "messages.yml");
	FileConfiguration msgConfig = YamlConfiguration.loadConfiguration(msgConfigFile);
	
	public static Map<UUID, UUID> request = new HashMap<UUID, UUID>();

	public ValkEssentials() {
		name = getDescription().getName();
		main = this;
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
		pm.registerEvents(new Msc(), this);
		pm.registerEvents(new Perms(), this);
		pm.registerEvents(new RankChangeEventListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
	}

	private void registerCommands() {
		getCommand("help").setExecutor(new Help());
		getCommand("home").setExecutor(new Home());
		getCommand("sethome").setExecutor(new Home());
		getCommand("delhome").setExecutor(new Home());
		getCommand("fly").setExecutor(new Fly());
		getCommand("kick").setExecutor(new Kick());
		getCommand("mute").setExecutor(new Mute());
		getCommand("rank").setExecutor(new Rank());
		getCommand("speed").setExecutor(new Speed());
		getCommand("color").setExecutor(new Color());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("whisper").setExecutor(new Whisper());
		getCommand("gamemode").setExecutor(new Gamemode());
		getCommand("invsee").setExecutor(new Invsee());
		getCommand("back").setExecutor(new Back());
		getCommand("tpa").setExecutor(new Tpa());
		getCommand("tpaccept").setExecutor(new Tpa());
		getCommand("tpdeny").setExecutor(new Tpa());
		getCommand("tpahere").setExecutor(new Tpa());
		getCommand("butcher").setExecutor(new Butcher());
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
			msgConfig.set("messages.chat", "&a%player%&7: &7#color#%message%");
		}

		if (!msgConfig.isSet("messages.blockedwords")) {
			List<String> msgs = Arrays.asList("fuck", "nigger", "cunt", "cunts", "bitch", "whore", "slut",
					"motherfucker", "fucker", "blowjob", "dick", "kunt", "faggot", "niglet", "prick", "testicles",
					"penis", "nigga", "cunt", "cock", "penis", "negro", "fag", "faggit", "nibba", "niger", "shithead",
					"fack", "fuk", "fukk", "pussy", "bittch", "bich", "dic", "asshole", "coock", "fuc");
			msgConfig.set("messages.blockedwords", msgs);
		}
		if (!msgConfig.isSet("messages.blockedreplace")) {
			msgConfig.set("messages.blockedreplace", "meeowww");
		}

		if (!msgConfig.isSet("messages.ranks")) {
			List<String> msgs = Arrays.asList(
					"Donator: &7{&bDonator&7} ",
					"Trial Developer: &7{&dTrial&7-&dDev&7}",
					"Developer: &7{&dDev&7} ",
					"Trial Builder: &7{&6Trial&7-&6Builder&7}",
					"Builder: &7{&6Builder&7} ",
					"Trial Mod: &7{&2Trial&7-&2Mod&7}",
					"Mod: &7{&2Mod&7} ", 
					"Trial Admin: &7{&cTrial&7-&cAdmin&7}",
					"Admin: &7{&cAdmin&7} ", 
					"Co-Owner: &7{&fCo&7-&fOwner&7} ",
					"Owner: &7{&fOwner&7} ");
			msgConfig.set("messages.ranks", msgs);
		}
		if (!msgConfig.isSet("messages.defaultrank")) {
			msgConfig.set("messages.defaultrank", "&7{&2User&7} ");
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
					"&8{&b-&8} &3%player% &7learned that kittens are really tough",
					"&8{&b-&8} &3%player% &7divorced their cats", "&8{&b-&8} &3%player% &7ran away from the cats");
			msgConfig.set("messages.leave", msgs);
		}

		if (!msgConfig.isSet("messages.kick")) {
			List<String> msgs = Arrays.asList("&8{&b-&8} &3%player% &7was kicked");
			msgConfig.set("messages.kick", msgs);
		}

		if (!msgConfig.isSet("messages.death")) {
			List<String> msgs = Arrays.asList("&3%player% &7got devoured by a kitten",
					"&3%player% &7had a date with a cat", "&3%player% &7thought they were a cat",
					"&3%player% &7tried to tame a kitten", "&3%player%&7's limbs were smashed",
					"&3%player% &7learned to keep their distance from kittens and cats alike", "&3%player% &7fed a cat",
					"&3%player% &7got a cat as a pet", "&3%player% &7turned into cat food",
					"&3%player% &7thought cats could fly", "&3%player% &7turned into a cat",
					"&3%player% &7got married to a cat", "&3%player% &7got candy from a cat",
					"&3%player% &7became a kittens slave", "&3%player% &7made a kitten really mad.",
					"&3%player% &7was scratched to death by cute kittens.");
			msgConfig.set("messages.death", msgs);
		}
	}
}
