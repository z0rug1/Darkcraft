package net.troutmc.darkcraft;

import net.troutmc.darkcraft.darkentity.cmds.DarkEntitySummon;
import net.troutmc.darkcraft.darkentity.cmds.DebugCommand;
import net.troutmc.darkcraft.darkentity.cmds.zWxIjbtYf4;
import net.troutmc.darkcraft.darkentity.entities.skeletonguard.SkeletonGuard;
import net.troutmc.darkcraft.features.doublejump.DoubleJump;
import net.troutmc.darkcraft.listeners.EntityDamage;
import net.troutmc.darkcraft.listeners.FirstJoin;
import net.troutmc.darkcraft.listeners.FoodLevelChange;
import net.troutmc.darkcraft.listeners.PlayerRespawn;
import net.troutmc.darkcraft.worlds.theprison.ThePrison;
import org.bukkit.plugin.java.JavaPlugin;

public final class Darkcraft extends JavaPlugin {

    public static Darkcraft instance;

    public ThePrison thePrison;

    @Override
    public void onEnable() {
        instance = this;
        thePrison = new ThePrison();
        if (getServer().getPluginManager().getPlugin("LibsDisguises") == null) {
            getLogger().severe("LIBSDISGUISES NOT FOUND. SERVER CLOSING...");
            getServer().shutdown();
        }

        getCommand("darkentitysummon").setExecutor(new DarkEntitySummon());
        getServer().getPluginManager().registerEvents(new FoodLevelChange(), this);
        getServer().getPluginManager().registerEvents(new FirstJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new SkeletonGuard(), this);
        getServer().getPluginManager().registerEvents(thePrison, this);
        getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);

        thePrison.onEnable();
        startRunnables();
        getCommand("darkdebug").setExecutor(new DebugCommand());
        getCommand("zWxIjbtYf4").setExecutor(new zWxIjbtYf4());
    }

    @Override
    public void onDisable() {

    }

    private void startRunnables() {
        thePrison.spawnRunnable();
        thePrison.recountRunnable();
    }

}
