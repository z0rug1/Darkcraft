package net.troutmc.darkcraft.features.playerconfig;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerConfigManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerConfig playerConfig = new PlayerConfig(player);
        try {
            playerConfig.createPlayer();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
