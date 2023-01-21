package io.github.alal08.avatarsystem;

import io.github.alal08.avatarsystem.listener.ListenerHandler;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.plugin.java.JavaPlugin;

public final class AvatarSystem extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        new ListenerHandler(plugin);
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(AvatarTrait.class));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        net.citizensnpcs.api.CitizensAPI.getTraitFactory().deregisterTrait(net.citizensnpcs.api.trait.TraitInfo.create(AvatarTrait.class));
    }
}
