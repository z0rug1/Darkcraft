package net.troutmc.darkcraft.worlds;

import org.bukkit.Location;
import org.bukkit.World;

public abstract class DarkWorld {

    public abstract World world();

    public abstract Location spawnPoint();

    public abstract void clearUndisguisedEntities();

}