package it.ajstudios.wdyc;
import it.ajstudios.wdyc.commands.AJendss;
import it.ajstudios.wdyc.commands.AJreport;
import it.ajstudios.wdyc.commands.AJreportlist;
import it.ajstudios.wdyc.commands.AJss;
import it.ajstudios.wdyc.events.AJevents;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.*;
import java.nio.file.Files;

/*






Voglio solo ricordare che questo plugin mi è costato molta sanità mentale che non recupererò più, FANCULO BUNGEECORD. Ciao sanità mentale <3.







*/

public final class WDYC extends Plugin{

    private static WDYC instance;
    private File file;
    public static Configuration configuration;
    public String prefix = "§lAJ§l§eStudios§7> ";

    @Override
    public void onEnable() {
        setInstance(this);

        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new AJss());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new AJendss());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new AJreport());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new AJreportlist());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new AJevents());
        getLogger().info("WDYC enabled successfully");
        getLogger().info("Plugin made by AJstudios. Check us on github");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled Succesfully.");
    }

    public static WDYC getInstance() {
        return instance;
    }

    private static void setInstance(WDYC instance) {
        WDYC.instance = instance;
    }


}
