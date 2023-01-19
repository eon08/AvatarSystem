package io.github.alal08.avatarsystem.util.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.function.Consumer;

public class GUIManager implements InventoryHolder {

    private final Inventory inventory;
    final int size;
    final String title;
    final InventoryType inventoryType;
    final HashMap<Integer, Consumer<InventoryClickEvent>> onClick;
    final Consumer<InventoryCloseEvent> onClose;
    final Consumer<InventoryOpenEvent> onOpen;
    final boolean notClickable;

    public GUIManager(@NotNull InventoryType inventoryType, int size, String title,
                      HashMap<Integer, Consumer<InventoryClickEvent>> onClick,
                      Consumer<InventoryCloseEvent> onClose,
                      Consumer<InventoryOpenEvent> onOpen,
                      boolean notClickable) {
        this.inventoryType = inventoryType;
        this.size = size;
        this.title = title;
        this.onClick = onClick;
        this.onClose = onClose;
        this.onOpen = onOpen;
        if (inventoryType.equals(InventoryType.CHEST)) {
            inventory = Bukkit.createInventory(this, this.size, this.title);
        } else {
            inventory = Bukkit.createInventory(this, inventoryType, this.title);
        }
        this.notClickable = notClickable;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
