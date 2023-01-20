package io.github.alal08.avatarsystem;

import io.github.alal08.avatarsystem.util.YamlManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Inventory;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Avatar {

    private static final YamlManager dataAvatarYaml = new YamlManager("data", "avatar");

    public static void initAvatar(@NotNull Player player) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName(), player.getLocation());
        npc.destroy();
    }

    public static void disconnectAvatar(@NotNull Player player) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName(), player.getLocation());
        AvatarTrait avatarTrait = npc.getOrAddTrait(AvatarTrait.class);
        avatarTrait.toAvatar(npc, player);
        npc.addTrait(avatarTrait);
    }

    public static void connectAvatar(@NotNull Player player) {
        String npcUUID = (String) dataAvatarYaml.get(player.getUniqueId().toString());
        if (npcUUID == null) return;
        NPC npc = CitizensAPI.getNPCRegistry().getByUniqueId(UUID.fromString(npcUUID));
        if (npc == null) return;
        Player playerNPC = (Player) npc.getEntity();
        if (playerNPC == null) {
            if (Boolean.FALSE.equals(player.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)))
                player.getInventory().clear();
            player.teleport(new Location(player.getWorld(), 0, Integer.MIN_VALUE, 0));
            player.setHealth(0);
            npc.destroy();
            return;
        }
        player.getInventory().setContents(npc.getOrAddTrait(Inventory.class).getContents());
        player.teleport(playerNPC.getLocation());
        player.setHealth(playerNPC.getHealth());
        player.addPotionEffects(((Player) npc.getEntity()).getActivePotionEffects());
        npc.destroy();
    }
}
