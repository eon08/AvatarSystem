package io.github.alal08.avatarsystem.listener;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ListenerHandler {

    public ListenerHandler(@NotNull JavaPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new NPCListener(), plugin);
        pluginManager.registerEvents(new PlayerListener(), plugin);
    }
}