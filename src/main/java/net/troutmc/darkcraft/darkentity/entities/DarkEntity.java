package net.troutmc.darkcraft.darkentity.entities;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public abstract class DarkEntity {

    public abstract String entityName();

    public abstract String entityNameFormatted();

    public abstract String skinOwner();

    public abstract LivingEntity summon(Location location);

}
