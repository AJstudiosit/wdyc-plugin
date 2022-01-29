package it.ajstudios.wdyc.events.customevents;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;

public class PlayerEndControl extends Event {

    private final ProxiedPlayer player;

    public PlayerEndControl(ProxiedPlayer player) {
        this.player = player;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

}