package io.github.alal08.avatarsystem;

import io.github.alal08.avatarsystem.util.yaml.YamlManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Inventory;
import net.citizensnpcs.api.trait.trait.Owner;
import org.bukkit.GameRule;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Avatar {

    private static final YamlManager dataAvatarYaml = new YamlManager("data", "avatar");

    private static LivingEntity livingEntityFromEntity(Entity entity) {
        if (entity instanceof LivingEntity) {
            return (LivingEntity) entity;
        }
        return null;
    }

    public static void disconnectAvatar(@NotNull Player player) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, player.getName(), player.getLocation());
        dataAvatarYaml.put(player.getUniqueId().toString(), npc.getUniqueId().toString());
        npc.getOrAddTrait(Owner.class).setOwner(player.getUniqueId());
        npc.setProtected(false);
        ((Player) npc.getEntity()).setGameMode(player.getGameMode());
        ((LivingEntity) npc.getEntity()).setHealth(player.getHealth());
        PlayerInventory playerInventory = player.getInventory();
        Inventory inventory = npc.getOrAddTrait(Inventory.class);
        inventory.setContents(playerInventory.getContents());
        Equipment equipment = npc.getOrAddTrait(Equipment.class);
        equipment.set(Equipment.EquipmentSlot.OFF_HAND, playerInventory.getItemInOffHand());
        equipment.set(Equipment.EquipmentSlot.HELMET, playerInventory.getHelmet());
        equipment.set(Equipment.EquipmentSlot.CHESTPLATE, playerInventory.getChestplate());
        equipment.set(Equipment.EquipmentSlot.LEGGINGS, playerInventory.getLeggings());
        equipment.set(Equipment.EquipmentSlot.BOOTS, playerInventory.getBoots());
        ((Player) npc.getEntity()).getInventory().setHeldItemSlot(playerInventory.getHeldItemSlot());
        ((Player) npc.getEntity()).addPotionEffects(player.getActivePotionEffects());
    }

    public static void connectAvatar(@NotNull Player player) {
        String npcUUID = (String) dataAvatarYaml.get(player.getUniqueId().toString());
        if (npcUUID == null) return;
        NPC npc = CitizensAPI.getNPCRegistry().getByUniqueId(UUID.fromString(npcUUID));
        if (npc == null) return;
        LivingEntity entity = livingEntityFromEntity(npc.getEntity());
        if (entity == null) {
            if (Boolean.FALSE.equals(player.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)))
                player.getInventory().clear();
            player.setHealth(0);
            npc.destroy();
            return;
        }
        player.teleport(entity.getLocation());
        player.setHealth(entity.getHealth());
        player.getInventory().setContents(npc.getOrAddTrait(Inventory.class).getContents());
        player.addPotionEffects(((Player) npc.getEntity()).getActivePotionEffects());
        npc.destroy();
    }
}
