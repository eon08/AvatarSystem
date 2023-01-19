package io.github.alal08.avatarsystem;

import io.github.alal08.avatarsystem.util.yaml.YamlHelper;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Owner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Avatar {

    private static LivingEntity livingEntityFromEntity(Entity entity) {
        if (entity instanceof LivingEntity) {
            return (LivingEntity) entity;
        }
        return null;
    }

    public static void initAvatar(@NotNull Player player) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName(), player.getLocation());
        YamlHelper.set("data", "avatar", player.getUniqueId().toString(), npc.getUniqueId().toString());
        npc.destroy();
    }

    public static void disconnectAvatar(@NotNull Player player) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName(), player.getLocation());
        livingEntityFromEntity(npc.getEntity()).setHealth(player.getHealth());
        Equipment equipment = npc.getOrAddTrait(Equipment.class);
        PlayerInventory inventory = player.getInventory();
        equipment.set(Equipment.EquipmentSlot.HELMET, inventory.getHelmet());
        equipment.set(Equipment.EquipmentSlot.CHESTPLATE, inventory.getChestplate());
        equipment.set(Equipment.EquipmentSlot.LEGGINGS, inventory.getLeggings());
        equipment.set(Equipment.EquipmentSlot.BOOTS, inventory.getBoots());
        npc.setProtected(false);
        Owner owner = npc.getOrAddTrait(Owner.class);
        owner.setOwner(player.getUniqueId());
        YamlHelper.set("data", "avatar", player.getUniqueId().toString(), npc.getUniqueId().toString());
        YamlHelper.set("playerdata", player.getUniqueId().toString(), "inventory", inventory);
    }

    public static void connectAvatar(@NotNull Player player) {
        String npcUUID = (String) YamlHelper.get("data", "avatar", player.getUniqueId().toString());
        if (npcUUID == null) return;
        NPC npc = CitizensAPI.getNPCRegistry().getByUniqueId(UUID.fromString(npcUUID));
        LivingEntity entity = livingEntityFromEntity(npc.getEntity());
        if (entity == null) {
            player.setHealth(0);
            npc.destroy();
            return;
        }
        player.teleport(entity.getLocation());
        player.setHealth(entity.getHealth());
        npc.destroy();
    }
}
