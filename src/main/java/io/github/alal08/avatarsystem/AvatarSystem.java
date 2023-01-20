package io.github.alal08.avatarsystem;

import io.github.alal08.avatarsystem.command.CommandHandler;
import io.github.alal08.avatarsystem.listener.ListenerHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class AvatarSystem extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        new CommandHandler(plugin);
        new ListenerHandler(plugin);
        net.citizensnpcs.api.CitizensAPI.getTraitFactory().registerTrait(net.citizensnpcs.api.trait.TraitInfo.create(AvatarTrait.class));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
