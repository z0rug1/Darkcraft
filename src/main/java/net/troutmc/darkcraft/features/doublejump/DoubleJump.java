package net.troutmc.darkcraft.features.doublejump;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.troutmc.darkcraft.Darkcraft;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class DoubleJump implements Listener {

    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onPlayerFly(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {

            if (cooldown.containsKey(player.getUniqueId())) {
                if (cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    long timeLeft = (cooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000;
                    player.setAllowFlight(false);
                    e.setCancelled(true);
                    return;
                }
            }

            cooldown.put(player.getUniqueId(), System.currentTimeMillis() + (2 * 1200));

            e.setCancelled(true);
            player.setAllowFlight(true);
            player.setFlying(true);
            player.setVelocity(player.getLocation().getDirection().multiply(1.13D).setY(0.6D));
            player.playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 15);
            player.setAllowFlight(false);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if ((player.getGameMode() != GameMode.CREATIVE) && (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
            player.setAllowFlight(true);
        }
    }

}
