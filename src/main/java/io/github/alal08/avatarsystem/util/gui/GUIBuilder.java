package io.github.alal08.avatarsystem.util.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.function.Consumer;

public class GUIBuilder {

    private int size;
    private String title;
    private InventoryType inventoryType = InventoryType.CHEST;
    private final HashMap<Integer, Consumer<InventoryClickEvent>> onClick = new HashMap<>();
    private Consumer<InventoryCloseEvent> onClose = gui -> {};
    private Consumer<InventoryOpenEvent> onOpen = gui -> {};
    private boolean notClickable = true;

    private GUIBuilder() {}

    @Contract(" -> new")
    public static @NotNull GUIBuilder builder() {
        return new GUIBuilder();
    }

    public GUIBuilder size(int size) {
        this.size = size;
        return this;
    }

    public GUIBuilder title(String title) {
        this.title = title;
        return this;
    }

    public GUIBuilder type(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
        return this;
    }

    public GUIBuilder onClick(int slot, Consumer<InventoryClickEvent> consumer) {
        onClick.put(slot, consumer);
        return this;
    }

    public GUIBuilder onClose(Consumer<InventoryCloseEvent> consumer) {
        onClose = consumer;
        return this;
    }

    public GUIBuilder onOpen(Consumer<InventoryOpenEvent> consumer) {
        onOpen = consumer;
        return this;
    }

    public GUIBuilder notClickable(boolean b) {
        notClickable = b;
        return this;
    }

    public GUIManager build() {
        return new GUIManager(inventoryType, size, title, onClick, onClose, onOpen, notClickable);
    }
}
