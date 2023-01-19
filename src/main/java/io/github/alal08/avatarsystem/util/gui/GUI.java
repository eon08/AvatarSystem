package io.github.alal08.avatarsystem.util.gui;

import io.github.alal08.avatarsystem.util.ItemHelper;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class GUI {

    public static void soldier(@NotNull Player player, @NotNull LivingEntity entity) {
        GUIManager guiManager = GUIBuilder
                .builder()
                .title("NPC Equipment")
                .size(18)
                .onClick(0, gui -> {
                    for (int i = 0; i < 9; i++) {
                        if (gui.getSlot() == i) {
                            gui.setCancelled(true);
                        }
                    }
                })
                .notClickable(false)
                .build();
        Inventory inventory = guiManager.getInventory();
        EntityEquipment equipment = entity.getEquipment();
        inventory.setItem(0, ItemHelper.createItem(Material.IRON_SWORD, 1, "HAND"));
        inventory.setItem(1, ItemHelper.createItem(Material.SHIELD, 1, "OFFHAND"));
        inventory.setItem(2, ItemHelper.createItem(Material.IRON_HELMET, 1, "HELMET"));
        inventory.setItem(3, ItemHelper.createItem(Material.IRON_CHESTPLATE, 1, "CHESTPLATE"));
        inventory.setItem(4, ItemHelper.createItem(Material.IRON_LEGGINGS, 1, "LEGGINGS"));
        inventory.setItem(5, ItemHelper.createItem(Material.IRON_BOOTS, 1, "BOOTS"));
        inventory.setItem(11, equipment.getHelmet());
        inventory.setItem(12, equipment.getChestplate());
        inventory.setItem(13, equipment.getLeggings());
        inventory.setItem(14, equipment.getBoots());
        player.openInventory(inventory);
    }
}
