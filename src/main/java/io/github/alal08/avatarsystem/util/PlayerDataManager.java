package io.github.alal08.avatarsystem.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PlayerDataManager {

    public static @Nullable Player clone(@NotNull final Player player) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), player.getName());
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = server.C();
        if (worldServer == null) {
            return null;
        }
        EntityPlayer entity = new EntityPlayer(server, worldServer, profile);
        Player target = entity.getBukkitEntity();
        target.setPlayerProfile(player.getPlayerProfile());
        return target;
    }

    public static @Nullable Player loadPlayer(@NotNull final OfflinePlayer offline) {
        if (!offline.hasPlayedBefore()) {
            return null;
        }
        GameProfile profile = new GameProfile(offline.getUniqueId(),
                offline.getName() != null ? offline.getName() : offline.getUniqueId().toString());
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = server.C();
        if (worldServer == null) {
            return null;
        }
        EntityPlayer entity = new EntityPlayer(server, worldServer, profile);
        Player target = entity.getBukkitEntity();
        if (target != null) {
            target.loadData();
        }
        return target;
    }
}
