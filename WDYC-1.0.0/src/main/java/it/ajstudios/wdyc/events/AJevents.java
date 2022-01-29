package it.ajstudios.wdyc.events;

import it.ajstudios.wdyc.WDYC;
import it.ajstudios.wdyc.events.customevents.PlayerEndControl;
import it.ajstudios.wdyc.events.customevents.PlayerStartControl;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;


public class AJevents implements Listener {

    HashMap<ProxiedPlayer, Boolean> serverPermissions = new HashMap<ProxiedPlayer, Boolean>();

    @EventHandler
    public void onPlayerStartControl(PlayerStartControl e) {
        ProxiedPlayer controllato = e.getPlayer();
        if(controllato.hasPermission("bungeecord.command.server")) {
            serverPermissions.put(controllato, Boolean.TRUE);
        } else {
            serverPermissions.put(controllato, Boolean.FALSE);
        }
        String permissions_to_remove_when_starting_ss = WDYC.configuration.getString("permissions_to_remove_when_starting_ss").replace("&", "§");
        String[] permissions = permissions_to_remove_when_starting_ss.split(",");
        for(String p : permissions) {
            controllato.setPermission(p,false);
        }
    }

    @EventHandler
    public void onPlayerEndControl(PlayerEndControl e) {
        ProxiedPlayer controllato = e.getPlayer();
        String permissions_to_remove_when_starting_ss = WDYC.configuration.getString("permissions_to_remove_when_starting_ss").replace("&", "§");
        String[] permissions = permissions_to_remove_when_starting_ss.split(",");
        for(String p : permissions) {
            controllato.setPermission(p,true);
        }
    }

}
