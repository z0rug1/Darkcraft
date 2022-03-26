package net.troutmc.darkcraft.listeners;

import net.troutmc.darkcraft.worlds.theprison.ThePrison;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!player.hasPlayedBefore()) {
            welcome(player);
        }
    }

    private void welcome(Player player) {
        player.teleport(new ThePrison().spawnPoint());
    }

}
