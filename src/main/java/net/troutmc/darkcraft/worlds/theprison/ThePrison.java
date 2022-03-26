package net.troutmc.darkcraft.worlds.theprison;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import net.troutmc.darkcraft.Darkcraft;
import net.troutmc.darkcraft.darkentity.entities.skeletonguard.SkeletonGuard;
import net.troutmc.darkcraft.worlds.DarkWorld;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class ThePrison extends DarkWorld implements Listener {

    @Override
    public World world() {
        return Bukkit.getWorld("theprison");
    }

    @Override
    public Location spawnPoint() {
        return new Location(world(), 1.5, 70.0, 2.0, 90, 0);
    }

    @Override
    public void clearUndisguisedEntities() {
        for (LivingEntity e : world().getLivingEntities()) {
            if (e instanceof Zombie) {
                Disguise disguise = DisguiseAPI.getDisguise(e);
                if (disguise == null) {
                    e.remove();
                }
            }
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Chicken && e.getEntity().getWorld().equals(world())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp() && player.getWorld().equals(world())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();
        perhapsClearUndisguisedEntities(player);
        if (player.getGameMode() == GameMode.SURVIVAL) {
            player.setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        perhapsClearUndisguisedEntities(player);
        if (player.getGameMode() == GameMode.SURVIVAL) {
            player.setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onGameModeChange(PlayerGameModeChangeEvent e) {
        Player player = e.getPlayer();
        perhapsClearUndisguisedEntities(player);
        if (e.getNewGameMode() == GameMode.SURVIVAL) {
            player.setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent e) {
        if (e.getEntity().getWorld().getName().equals(world().getName())) {
            entityListSec1.remove(e.getEntity());
            entityListSec2.remove(e.getEntity());
        }
    }

    private void perhapsClearUndisguisedEntities(Player player) {
        if (player == null) return;

        if (player.getWorld().equals(world())) {
            clearUndisguisedEntities();
        }
    }

    public void onEnable() {
        for (LivingEntity e : world().getLivingEntities()) {
            if (e instanceof Zombie) {
                e.remove();
            }
        }
    }

    private Location guardSpawnLocation(double x, double y, double z, int direction, int axis) {
        return new Location(world(), x, y, z, direction, axis);
    }

    public static final ArrayList<LivingEntity> entityListSec1 = new ArrayList<>();
    public static final ArrayList<LivingEntity> entityListSec2 = new ArrayList<>();

    private final Location[] entityLocListSec1 = {
            guardSpawnLocation(-26.5, 67.0, 28.5, 160, 0),
            guardSpawnLocation(-33.5, 68.0, 32.5, 113, 0),
            guardSpawnLocation(-46.2, 67.0, 32.5, -160, 0),
            guardSpawnLocation(-47.9, 67.0, 21.7, -46, 0),
            guardSpawnLocation(-41.5, 67.0, 21.5, 90, 0),
            guardSpawnLocation(-43.5, 67.0, 26.5, 20, 0)
    };

    private final Location[] entityLocListSec2 = {
            guardSpawnLocation(-47.5, 67.0, -4.5, 0, 0),
            guardSpawnLocation(-42.5, 67.0, -4.5, 0, 0),
            guardSpawnLocation(-42.5, 67.0, 4.5, 0, 0),
            guardSpawnLocation(-47.5, 67.0, 3.5, 0, 0)
    };

    Random r = new Random();

    private Location pickRandomLoc(Location[] locations) {
        if (locations == null)
            return null;
        return locations[r.nextInt(locations.length)];
    }

    SkeletonGuard skeletonGuard = new SkeletonGuard();

    public void spawnRunnable() {
        new BukkitRunnable() {
            public void run() {
                if (entityListSec1.size() <= 3) {
                    LivingEntity entity = skeletonGuard.summon(pickRandomLoc(entityLocListSec1));
                    entityListSec1.add(entity);
                }

                if (entityListSec2.size() <= 3) {
                    LivingEntity entity = skeletonGuard.summon(pickRandomLoc(entityLocListSec2));
                    entityListSec2.add(entity);
                }

            }
        }.runTaskTimer(Darkcraft.instance, 0L, 11L*20L);
    }

    private void recountList(ArrayList<LivingEntity> list) {
        if (list == null) {
            return;
        }

        LivingEntity toRemove = null;

        for (LivingEntity livingEntity : list) {
            boolean exists = false;
            for (LivingEntity e : world().getLivingEntities()) {
                if (e instanceof Zombie) {
                    if (!entityListSec1.contains(e) && !entityListSec2.contains(e)) {
                        e.remove();
                    }
                }

                if (e.getUniqueId().toString().equals(livingEntity.getUniqueId().toString())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                toRemove = livingEntity;
            }
        }

        if (toRemove != null) {
            list.remove(toRemove);
        }
    }

    public void recountRunnable() {

        new BukkitRunnable() {
            public void run() {
                recountList(entityListSec1);
                recountList(entityListSec2);
            }
        }.runTaskTimer(Darkcraft.instance, 0L, 100L);
    }

}
