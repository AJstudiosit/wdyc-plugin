package it.ajstudios.wdyc.commands;

import it.ajstudios.wdyc.WDYC;
import it.ajstudios.wdyc.events.customevents.PlayerStartControl;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AJss extends Command
{


    public AJss() {
        super("ss");
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if (sender instanceof ProxiedPlayer)
        {
            if (sender.hasPermission("wdyc.ss.use"))
            {
                ProxiedPlayer controllatore = (ProxiedPlayer) sender;
                if (args.length == 0)
                {
                    String not_enough_args = WDYC.configuration.getString("minus_args").replace("&", "§").replace("%staffer%", controllatore.getName());
                    controllatore.sendMessage(new ComponentBuilder(not_enough_args).create());
                }
                else if (args.length == 1)
                {

                    ProxiedPlayer controllato = ProxyServer.getInstance().getPlayer(args[0]);

                    if (controllato == null)
                    {
                        String controllato_non_online = WDYC.configuration.getString("not_online").replace("&", "§").replace("%staffer%", controllatore.getName());
                        controllatore.sendMessage(new ComponentBuilder(controllato_non_online).create());
                        return;
                    }


                    if (controllato.hasPermission("wdyc.ss.immunity"))
                    {
                        String ss_immunity = WDYC.configuration.getString("ss_immunity").replace("&", "§").replace("%staffer%", controllatore.getName()).replace("%player%", controllato.getName());

                        controllatore.sendMessage(new ComponentBuilder(ss_immunity).create());
                        return;
                    }
                    else
                    {
                        boolean global_msg = WDYC.configuration.getBoolean("ss_sendGlobalMsg");
                        if (global_msg)
                        {
                            String globalMsg = WDYC.configuration.getString("ss_globalMsg").replace("&", "§").replace("%staffer%", controllatore.getName()).replace("%player%", controllato.getName());

                            for (ProxiedPlayer p : controllato.getServer().getInfo().getPlayers())
                            {
                                p.sendMessage(new ComponentBuilder(globalMsg).create());
                            }
                        }

                        String ss_server = WDYC.configuration.getString("server_ss");

                        String staffer_message = WDYC.configuration.getString("staff_join_ss").replace("&", "§").replace("%staffer%", controllatore.getName().replace("%player%", controllato.getName()));

                        controllatore.sendMessage(new ComponentBuilder(staffer_message).create());

                        String player_message = WDYC.configuration.getString("player_join_ss").replace("&", "§").replace("%staffer%", controllatore.getName().replace("%player%", controllato.getName()));

                        controllato.sendMessage(new ComponentBuilder(player_message).create());

                        controllatore.connect(ProxyServer.getInstance().getServerInfo(ss_server));
                        controllato.connect(ProxyServer.getInstance().getServerInfo(ss_server));


                        ProxyServer.getInstance().getPluginManager().callEvent(new PlayerStartControl(controllato));
                    }




                }
            }
            else
            {
                String ss_usage = WDYC.configuration.getString("ss_usage").replace("&", "§");

                sender.sendMessage(new ComponentBuilder(ss_usage).create());
            }
        }
        else
        {
            String not_a_player = WDYC.configuration.getString("not_a_player").replace("&", "§");
            sender.sendMessage(new ComponentBuilder(not_a_player).create());
        }
    }
}




