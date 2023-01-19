package io.github.alal08.avatarsystem.util.yaml;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class YamlManager {

    static String path = System.getProperty("user.dir");

    static @NotNull FileConfiguration createYml(String dir, String fileName) {
        File folder = new File(new File(path + "\\" + dir).getAbsolutePath());
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file = new File(folder + "\\" + fileName + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            if (!file.exists()) {
                config.save(file);
            }
            config.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    static void saveYml(String dir, String fileName, @NotNull FileConfiguration config) {
        File folder = new File(new File(path + "\\" + dir).getAbsolutePath());
        File file = new File(folder + "\\" + fileName + ".yml");
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}