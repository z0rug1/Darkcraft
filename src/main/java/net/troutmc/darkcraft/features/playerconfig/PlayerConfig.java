package net.troutmc.darkcraft.features.playerconfig;

import net.troutmc.darkcraft.Darkcraft;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {

    FileConfiguration fileConfig;
    File file;
    Player player;

    public PlayerConfig(Player player) {
        this.player = player;
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
    }

    public void saveYML() {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
