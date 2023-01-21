package io.github.alal08.avatarsystem.util;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class CitizensHelper {

    public static NPC createNPC(EntityType type, String name, UUID uuid, Location location) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        int id = (int) registry.getClass().getMethod("generateIntegerId").invoke(registry);
        NPC npc = (NPC) registry.getClass()
                .getDeclaredMethod("createNPC", EntityType.class, UUID.class, Integer.class, String.class)
                .invoke(registry, type, uuid, id, name);
        npc.spawn(location, SpawnReason.PLUGIN);
        return npc;
    }

}
