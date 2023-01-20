package io.github.alal08.avatarsystem.listener;

import io.github.alal08.avatarsystem.AvatarTrait;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Inventory;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AvatarListener implements Listener {

    @EventHandler
    public void onAvatarDeath(@NotNull NPCDeathEvent event) {
        NPC npc = event.getNPC();
        if (!npc.hasTrait(AvatarTrait.class)) return;
        //UUID ownerUUID = npc.getOrAddTrait(Owner.class).getOwnerId();
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

    @EventHandler
    public void onNPCRightClick(@NotNull NPCRightClickEvent event) {
        NPC npc = event.getNPC();
        if (npc.hasTrait(AvatarTrait.class)) {
            event.getClicker().sendMessage("너는 아바타야!");
        }
    }
}
