package net.troutmc.darkcraft.features.playerconfig;

import net.troutmc.darkcraft.Darkcraft;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {

    FileConfiguration fileConfig;
    File file;
    OfflinePlayer player;

    public PlayerConfig(OfflinePlayer player) {
        this.player = player;
        this.file = new File(Darkcraft.instance.getDataFolder() + "/playerData/" + player.getUniqueId());
        this.fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    public void createPlayer() throws IOException {
        if (player == null) return;

        file = new File(Darkcraft.instance.getDataFolder() + "/playerData/" + player.getUniqueId());
        if (!file.exists()) {
            boolean result = file.createNewFile();
            if (!result) {
                Bukkit.getLogger().severe("FAILED TO CREATE PLAYER CONFIG FOR " + player.getName() + " UUID=" + player.getUniqueId());
            }
        }

        fileConfig = YamlConfiguration.loadConfiguration(file);
        saveYML();

        setString("player.uuid", player.getUniqueId().toString());
        setString("player.name", player.getName());
        setDouble("player.balance", 50);
    }

    public void saveYML() {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setString(String path, String string) {
        fileConfig.set(path, string);
        saveYML();
    }

    public void setInt(String path, int value) {
        fileConfig.set(path, value);
        saveYML();
    }

    public void setDouble(String path, double value) {
        fileConfig.set(path, value);
        saveYML();
    }

    public void setBoolean(String path, boolean value) {
        fileConfig.set(path, value);
        saveYML();
    }

    public String getString(String path) {
        return fileConfig.getString(path);
    }

    public int getInt(String path) {
        return fileConfig.getInt(path);
    }

    public double getDouble(String path) {
        return fileConfig.getDouble(path);
    }

    public boolean getBoolean(String path) {
        return fileConfig.getBoolean(path);
    }

}
