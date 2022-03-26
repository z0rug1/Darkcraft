package net.troutmc.darkcraft.features.launchpads;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class SlimeLaunchPads implements Listener {

    /* Cooldown is used for sound so there aren't two explosions (Can be caused when you slide over slime blocks) */
    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SLIME_BLOCK) {
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.5D).setY(1D));

            if (cooldown.containsKey(e.getPlayer().getUniqueId())) {
                if (cooldown.get(e.getPlayer().getUniqueId()) > System.currentTimeMillis()) {
                    return;
                }
            }

            cooldown.put(e.getPlayer().getUniqueId(), System.currentTimeMillis() + 200);

            e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_GENERIC_EXPLODE, 0.75f, 1f);
        }
    }
}
