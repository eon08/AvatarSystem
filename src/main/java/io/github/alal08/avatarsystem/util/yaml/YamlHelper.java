package io.github.alal08.avatarsystem.util.yaml;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.Nullable;

public class YamlHelper extends YamlManager {

    public static void set(String dir, String fileName, String path, Object value) {
        FileConfiguration config = createYml(dir, fileName);
        config.set(path, value);
        saveYml(dir, fileName, config);
    }

    public static Object get(String dir, String fileName, String path) {
        FileConfiguration config = createYml(dir, fileName);
        saveYml(dir, fileName, config);
        return config.get(path);
    }

    public static void setLocation(String dir, String fileName, String path, Location loc) {
        FileConfiguration config = createYml(dir, fileName);
        if (loc == null) {
            config.set(path, null);
        } else {
            String world = loc.getWorld().getName();
            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            float yaw = loc.getYaw();
            float pitch = loc.getPitch();
            config.set(path + ".world", world);
            config.set(path + ".x", x);
            config.set(path + ".y", y);
            config.set(path + ".z", z);
            config.set(path + ".yaw", yaw);
            config.set(path + ".pitch", pitch);
            saveYml(dir, fileName, config);
        }
    }

    public static @Nullable Location getLocation(String dir, String fileName, String path) {
        FileConfiguration config = createYml(dir, fileName);
        saveYml(dir, fileName, config);
        String worldString = config.getString(path + ".world");
        if (worldString == null) return null;
        World world = Bukkit.getWorld(worldString);
        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float yaw = (float) config.getDouble(path + ".yaw");
        float pitch = (float) config.getDouble(path + ".pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }
}
