package it.ajstudios.wdyc.commands;

// FINITI I COLLEGAMENTI AL CONFIG, CONTROLLARE BOOKMARKS

import it.ajstudios.wdyc.WDYC;
import it.ajstudios.wdyc.events.customevents.PlayerStartControl;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;
import java.util.HashMap;

public class AJreport extends Command {

    public AJreport() {
        super("report");
    }

    public static HashMap<ProxiedPlayer, String> reports = new HashMap<ProxiedPlayer, String>();

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if(sender instanceof ProxiedPlayer)
        {
            if(sender.hasPermission("wdyc.report.use"))
            {
                ProxiedPlayer reportatore = (ProxiedPlayer) sender;
                if(args.length == 0)
                {
                    String report_usage = WDYC.configuration.getString("report_usage").replace("&", "§");
                    reportatore.sendMessage(new ComponentBuilder(report_usage).create());
                }
                else if(args.length == 1)
                {
                    String minus_args = WDYC.configuration.getString("report_usage").replace("&", "§");
                    reportatore.sendMessage(new ComponentBuilder(minus_args).create());
                }
                else if(args.length >= 2 ) {
                    ProxiedPlayer reportato = ProxyServer.getInstance().getPlayer(args[0]);
                    if (reportato == null) {
                        String not_online = WDYC.configuration.getString("not_online").replace("&", "§");
                        reportatore.sendMessage(new ComponentBuilder(not_online).create());
                        return;
                    }
                    args[0] = "";
                    String motivo = Arrays.toString(args).replace("[", "").replace("]", "").replace(",", "").toUpperCase();

                    String successfully_report = WDYC.configuration.getString("successfully_report").replace("&", "§").replace("%player%", reportato.getName());
                    reportatore.sendMessage(new ComponentBuilder(successfully_report).create());
                    reports.put(reportato, motivo);

                    for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                        if (p.hasPermission("wdyc.report.list")) {
                            String staff_msg = WDYC.configuration.getString("staff_msg").replace("&", "§").replace("%player%", reportato.getName()).replace("%staffer%", reportatore.getName()).replace("%server%", reportatore.getServer().getInfo().getName().replace("%reason%", motivo));
                            p.sendMessage(new ComponentBuilder(staff_msg).create());
                        }
                    }
                }
            }
            else
            {
                String not_perms = WDYC.configuration.getString("not_perms").replace("&", "§");
                sender.sendMessage(new ComponentBuilder(not_perms).create());
            }
        }
        else
        {
            String not_a_player = WDYC.configuration.getString("not_a_player").replace("&", "§");
            sender.sendMessage(new ComponentBuilder(not_a_player).create());
        }
    }

}