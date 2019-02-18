package com.valkessentials.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RankChangeEvent extends Event {
	Player p;
	String rank;
	
	public RankChangeEvent(Player p, String rank) {
		this.p = p;
		this.rank = rank;
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public String getRank() {
		return rank;
	}
	
	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
}

