package net.troutmc.darkcraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if ((e.getEntity() instanceof Player) && (e.getCause() == EntityDamageEvent.DamageCause.FALL)) {
            e.setCancelled(true);
        }
    }

}
