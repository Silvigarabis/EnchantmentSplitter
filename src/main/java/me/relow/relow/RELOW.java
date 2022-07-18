package me.relow.relow;

import me.relow.relow.command.help;
import me.relow.relow.command.openRE;
import me.relow.relow.command.reload;
import me.relow.relow.event.click;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class RELOW extends JavaPlugin {
    private static File pluginFile;
    public static File getPluginFile(){
        return pluginFile;
    }
    private static Economy econ = null;

    @Override
    public void onEnable() {


        if(getServer().getPluginManager().getPlugin("PlayerPoints") == null){
            getLogger().info("未找到点卷插件！");
        }else {
            if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
                getLogger().info("已找到PlayerPoints插件！");
            }
        }

        // Plugin startup logic
        if (!setupEconomy()) {
            getLogger().info("未找到经济插件！");
        }else {
            getLogger().info("已找到Vault！");
        }

        getLogger().info("\033[36m= = = = = = = = = = = = = = = = = = = = = = \033[0m");
        getLogger().info("RE-LOW");
        getLogger().info("作者:StrawberryYu || QQ:2332742172");
        getLogger().info("\033[36m= = = = = = = = = = = = = = = = = = = = = = \033[0m");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        pluginFile = this.getDataFolder();
        Config.loadConfig();

        getServer().getPluginManager().registerEvents(new click(),this);
        getCommand("rel-open").setExecutor(new openRE());
        getCommand("rel-reload").setExecutor(new reload());
        getCommand("rel").setExecutor(new help());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
