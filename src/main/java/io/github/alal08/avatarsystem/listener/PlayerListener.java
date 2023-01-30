package io.github.alal08.avatarsystem.listener;

import io.github.alal08.avatarsystem.Avatar;
import io.github.alal08.avatarsystem.util.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Avatar.initAvatar(player);
        Avatar.connectAvatar(player);
        System.out.println(PlayerDataManager.clone(player).getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Avatar.disconnectAvatar(player);
    }

    @EventHandler
    public void onPlayerDeath(@NotNull PlayerDeathEvent event) {
        Player player = event.getPlayer();
        if (player.getLastDamageCause() == null) {
            event.setDeathMessage(null);
        }
    }
}
