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
        CitizensAPI.getTraitFactory().deregisterTrait(TraitInfo.create(AvatarTrait.class));
    }
}
