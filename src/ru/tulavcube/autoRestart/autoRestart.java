package ru.tulavcube.autoRestart;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tulavcube.autoRestart.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class autoRestart extends JavaPlugin {
    public Plugin self = this;

    public int thresh, timeout;
    public long timer;
    public boolean isRestarting = false;

    @Override
    public void onEnable() {
        getLogger().info("AutoRestart plugin started");
        saveDefaultConfig();
        updateSettings();
        getCommand("mem").setExecutor(new mem());
        getCommand("mem").setTabCompleter(new mem());

        getCommand("autorestart").setExecutor(new autorestart(this));
        getCommand("autorestart").setTabCompleter(new autorestart(this));

        new BukkitRunnable(){
            @Override
            public void run(){
                if((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1048576 < thresh) {
                    timer = System.currentTimeMillis();
                }
                if(System.currentTimeMillis() - timer > timeout* 1000L && !isRestarting){
                    Bukkit.broadcastMessage(ChatColor.GOLD + "WARNING! Server will restart in 10 seconds due to memory overflow");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            getServer().dispatchCommand(getServer().getConsoleSender(), "restart");
                        }
                    }.runTaskLater(self, 200);
                    isRestarting = true;
                }
            }
        }.runTaskTimer(this, 1, 1);
    }
    @Override
    public void onDisable(){
        getLogger().info("AutoRestart plugin stopped");
    }

    public void updateSettings() {
        thresh = getConfig().getInt("threshold");
        timeout = getConfig().getInt("timeout");
    }
}
