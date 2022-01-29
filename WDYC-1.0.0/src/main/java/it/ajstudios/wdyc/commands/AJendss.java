package it.ajstudios.wdyc.commands;

import it.ajstudios.wdyc.WDYC;
import it.ajstudios.wdyc.events.customevents.PlayerEndControl;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AJendss extends Command {

    public AJendss() {
        super("endss");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            if (sender.hasPermission("wdyc.ss.use")) {
                ProxiedPlayer controllatore = (ProxiedPlayer) sender;
                if (args.length == 0) {
                    String ends_ss_usage = WDYC.configuration.getString("endss_usage");
                    controllatore.sendMessage(new ComponentBuilder(ends_ss_usage).create());
                } else if (args.length == 1) {
                    String finish_server = WDYC.configuration.getString("server_finish");
                    ProxiedPlayer controllato = ProxyServer.getInstance().getPlayer(args[0]);

                    if (controllato == null)
                    {
                        String controllato_non_online = WDYC.configuration.getString("not_online").replace("&", "§").replace("%staffer%", controllatore.getName());
                        controllatore.sendMessage(new ComponentBuilder(controllato_non_online).create());
                        return;
                    }

                    String staffer_message = WDYC.configuration.getString("staff_left_ss").replace("&", "§").replace("%staffer%", controllatore.getName().replace("%player%", controllato.getName()));

                    controllatore.sendMessage(new ComponentBuilder(staffer_message).create());

                    String player_message = WDYC.configuration.getString("player_left_ss").replace("&", "§").replace("%staffer%", controllatore.getName().replace("%player%", controllato.getName()));

                    controllato.sendMessage(new ComponentBuilder(player_message).create());

                    controllatore.connect(ProxyServer.getInstance().getServerInfo(finish_server));
                    controllato.connect(ProxyServer.getInstance().getServerInfo(finish_server));

                    ProxyServer.getInstance().getPluginManager().callEvent(new PlayerEndControl(controllato));

                } else {
                    String ends_ss_usage = WDYC.configuration.getString("endss_usage");
                    controllatore.sendMessage(new ComponentBuilder(ends_ss_usage).create());
                }
            }
        } else {
            String not_a_player = WDYC.configuration.getString("not_a_player").replace("&", "§");
            sender.sendMessage(new ComponentBuilder(not_a_player).create());
        }

    }
}
