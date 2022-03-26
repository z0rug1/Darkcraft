package net.troutmc.darkcraft.features.playerconfig;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConfigManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerConfig playerConfig = getPlayerConfig(player);
        try {
            playerConfig.createPlayer();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static PlayerConfig getPlayerConfig(OfflinePlayer player) {
        return new PlayerConfig(player);
    }

}
