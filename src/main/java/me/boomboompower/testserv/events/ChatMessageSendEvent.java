package me.boomboompower.testserv.events;

/*
* Made for TestServCore
* by boomboompower 12/05/2016
*/

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChatMessageSendEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private String message;
    private boolean cancelled = false;

    public ChatMessageSendEvent(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
