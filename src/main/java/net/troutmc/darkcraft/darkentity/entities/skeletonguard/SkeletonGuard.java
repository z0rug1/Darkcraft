package net.troutmc.darkcraft.darkentity.entities.skeletonguard;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.LibsDisguises;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.troutmc.darkcraft.Darkcraft;
import net.troutmc.darkcraft.darkentity.HeathBar;
import net.troutmc.darkcraft.darkentity.entities.DarkEntity;
import net.troutmc.darkcraft.worlds.theprison.ThePrison;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SkeletonGuard extends DarkEntity implements Listener {

    @Override
    public String entityName() {
        return "SKELETON_GUARD";
    }

    @Override
    public String entityNameFormatted() {
        return "Skeleton Guard";
    }

    @Override
    public String skinOwner() {
        return "Qbi189";
    }

    @Override
    public LivingEntity summon(Location location) {
        Zombie entity = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.26f);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 99999999, 0, true, false));
        entity.setAdult();

        PlayerDisguise disguise = new PlayerDisguise(skinOwner());
        disguise.setViewSelfDisguise(false);

        entity.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(25.0D);
        entity.setHealth(25.0);

        disguise.setName(HeathBar.getFormat("Skeleton Guard", entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue(), entity.getHealth()));

        DisguiseAPI.disguiseToAll(entity, disguise);
        return entity;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity().getWorld().getName().equals(Darkcraft.instance.thePrison.world().getName())) {
            if (e.getEntity() instanceof Zombie) {
                Zombie zombie = (Zombie)e.getEntity();
                PlayerDisguise disguise = (PlayerDisguise) DisguiseAPI.getDisguise(e.getEntity());
                if (disguise == null) return;
                new BukkitRunnable() {
                    public void run() {
                        disguise.setName(HeathBar.getFormat("Skeleton Guard", zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue(), zombie.getHealth()));
                    }
                }.runTaskLater(Darkcraft.instance, 1L);
            }
        }
    }

}
