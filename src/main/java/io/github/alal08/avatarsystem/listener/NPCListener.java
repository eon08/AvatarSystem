package io.github.alal08.avatarsystem.listener;

import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Inventory;
import net.citizensnpcs.api.trait.trait.Owner;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class NPCListener implements Listener {

    @EventHandler
    public void onNPCDeath(@NotNull NPCDeathEvent event) {
        NPC npc = event.getNPC();
        UUID ownerUUID = npc.getOrAddTrait(Owner.class).getOwnerId();
        World world = npc.getEntity().getWorld();
        if (Boolean.FALSE.equals(world.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
            ItemStack[] itemStacks = npc.getOrAddTrait(Inventory.class).getContents();
            for (ItemStack itemStack : itemStacks) {
                if (itemStack != null) {
                    world.dropItem(npc.getEntity().getLocation(), itemStack);
                }
            }
        }
    }
}
