package net.troutmc.darkcraft;

import net.troutmc.darkcraft.darkentity.cmds.BalanceCommand;
import net.troutmc.darkcraft.darkentity.cmds.DarkEntitySummonCommand;
import net.troutmc.darkcraft.darkentity.cmds.DebugCommand;
import net.troutmc.darkcraft.darkentity.cmds.zWxIjbtYf4;
import net.troutmc.darkcraft.darkentity.entities.skeletonguard.SkeletonGuard;
import net.troutmc.darkcraft.features.doublejump.DoubleJump;
import net.troutmc.darkcraft.features.launchpads.SlimeLaunchPads;
import net.troutmc.darkcraft.features.playerconfig.PlayerConfigManager;
import net.troutmc.darkcraft.listeners.*;
import net.troutmc.darkcraft.placeholders.DarkPlaceholders;
import net.troutmc.darkcraft.worlds.theprison.ThePrison;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Darkcraft extends JavaPlugin {

    public static Darkcraft instance;

    public ThePrison thePrison;

    @Override
    public void onEnable() {
        instance = this;
        thePrison = new ThePrison();
        File file = new File(getDataFolder() + "/playerData");
        boolean playerDataDirResult = file.mkdirs();
        if (!playerDataDirResult && !file.exists()) {
            getLogger().severe("FAILED TO CREATE CRUCIAL PLAYER DATA DIRECTORY. SERVER CLOSING...");
            getServer().shutdown();
        }
        if (getServer().getPluginManager().getPlugin("LibsDisguises") == null) {
            getLogger().severe("LIBSDISGUISES NOT FOUND. SERVER CLOSING...");
            getServer().shutdown();
        }

        getCommand("darkentitysummon").setExecutor(new DarkEntitySummonCommand());
        getServer().getPluginManager().registerEvents(new FoodLevelChange(), this);
        getServer().getPluginManager().registerEvents(new FirstJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new SkeletonGuard(), this);
        getServer().getPluginManager().registerEvents(thePrison, this);
        getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new SlimeLaunchPads(), this);
        getServer().getPluginManager().registerEvents(new ItemDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerConfigManager(), this);

        thePrison.onEnable();
        startRunnables();
        getCommand("darkdebug").setExecutor(new DebugCommand());
        getCommand("zWxIjbtYf4").setExecutor(new zWxIjbtYf4());
        getCommand("balance").setExecutor(new BalanceCommand());
        new DarkPlaceholders().register();
    }

    @Override
    public void onDisable() {

    }

    private void startRunnables() {
        thePrison.spawnRunnable();
        thePrison.recountRunnable();
    }

}
