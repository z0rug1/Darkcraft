package net.troutmc.darkcraft.listeners;

import net.troutmc.darkcraft.worlds.theprison.ThePrison;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        e.setRespawnLocation(getRespawnLocation(e.getPlayer()));
    }

    public Location getRespawnLocation(Player player) {
        ThePrison prison = new ThePrison();
        if (player.getWorld().getName().equals(prison.world().getName())) {
            return prison.spawnPoint();
        }
        throw new RuntimeException("PlayerDeath: getRespawnLocation: Did not have a handler for " + player.getName() + "'s world: " + player.getWorld().getName());
    }

}
