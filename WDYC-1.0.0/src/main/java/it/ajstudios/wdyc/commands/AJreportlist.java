package it.ajstudios.wdyc.commands;

import it.ajstudios.wdyc.WDYC;
import it.ajstudios.wdyc.events.customevents.PlayerStartControl;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;

public class AJreportlist extends Command {

    public AJreportlist() {
        super("reportlist");
    }

    public HashMap<ProxiedPlayer, String> reports = new HashMap<ProxiedPlayer, String>();

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if(sender instanceof ProxiedPlayer)
        {
            if(sender.hasPermission("wdyc.report.list"))
            {
                sender.sendMessage(new ComponentBuilder("Reports: ").color(ChatColor.DARK_PURPLE).create());
                AJreport.reports.forEach((k, v) -> {
                    sender.sendMessage(new ComponentBuilder(k.getName() + ": " + v + "\n").color(ChatColor.LIGHT_PURPLE).create());
                });
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