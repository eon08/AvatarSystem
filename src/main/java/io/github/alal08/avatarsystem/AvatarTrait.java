package io.github.alal08.avatarsystem;

import io.github.alal08.avatarsystem.util.YamlManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Inventory;
import net.citizensnpcs.api.trait.trait.Owner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class AvatarTrait extends Trait {

    private static final YamlManager dataAvatarYaml = new YamlManager("data", "avatar");

    public AvatarTrait() {
        super("avatarTrait");
    }

    public void toAvatar(@NotNull NPC npc, @NotNull Player player) {
        dataAvatarYaml.put(player.getUniqueId().toString(), npc.getUniqueId().toString());
        npc.getOrAddTrait(Owner.class).setOwner(player.getUniqueId());
        npc.setProtected(false);
        PlayerInventory playerInventory = player.getInventory();
        Inventory inventory = npc.getOrAddTrait(Inventory.class);
        inventory.setContents(playerInventory.getContents());
        Equipment equipment = npc.getOrAddTrait(Equipment.class);
        equipment.set(Equipment.EquipmentSlot.OFF_HAND, playerInventory.getItemInOffHand());
        equipment.set(Equipment.EquipmentSlot.HELMET, playerInventory.getHelmet());
        equipment.set(Equipment.EquipmentSlot.CHESTPLATE, playerInventory.getChestplate());
        equipment.set(Equipment.EquipmentSlot.LEGGINGS, playerInventory.getLeggings());
        equipment.set(Equipment.EquipmentSlot.BOOTS, playerInventory.getBoots());
        ((Player) npc.getEntity()).setGameMode(player.getGameMode());
        ((Player) npc.getEntity()).setHealth(player.getHealth());
        ((Player) npc.getEntity()).getInventory().setHeldItemSlot(playerInventory.getHeldItemSlot());
        ((Player) npc.getEntity()).addPotionEffects(player.getActivePotionEffects());
        CitizensAPI.getNPCRegistry().saveToStore();
    }
}
