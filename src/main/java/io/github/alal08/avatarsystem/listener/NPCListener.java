package io.github.alal08.avatarsystem.listener;

import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Owner;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class NPCListener implements Listener {

    @EventHandler
    public void onNPCDeath(@NotNull NPCDeathEvent event) {
        NPC npc = event.getNPC();
        UUID ownerUUID = npc.getOrAddTrait(Owner.class).getOwnerId();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(ownerUUID);
    }
}
