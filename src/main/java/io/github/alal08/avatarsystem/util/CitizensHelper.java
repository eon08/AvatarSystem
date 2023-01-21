package io.github.alal08.avatarsystem.util;

import lombok.SneakyThrows;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCDataStore;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class CitizensHelper {

    @SneakyThrows
    public static NPC createNPC(EntityType type, String name, UUID uuid, Location location) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        Field savesField = registry.getClass().getDeclaredField("saves");
        savesField.setAccessible(true);
        NPCDataStore saves = (NPCDataStore) savesField.get(registry);
        int id = saves.createUniqueNPCId(registry);
        NPC npc = registry.createNPC(type, uuid, id, name);
        npc.spawn(location, SpawnReason.PLUGIN);
        return npc;
    }

}
