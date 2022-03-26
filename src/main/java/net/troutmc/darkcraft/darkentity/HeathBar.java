package net.troutmc.darkcraft.darkentity;

import org.bukkit.ChatColor;

import java.math.BigDecimal;

public class HeathBar {

    public static ChatColor getHealthColor(double maxHealth, double health) {
        if (health < maxHealth / 3)
            return ChatColor.DARK_RED;
        else if (health < maxHealth / 2)
            return ChatColor.YELLOW;
        else
            return ChatColor.GREEN;
    }

    public static String getFormat(String name, double maxHealth, double health) {
        BigDecimal dec = new BigDecimal(String.valueOf(health));
        double formattedHealth = dec.setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        return getHealthColor(maxHealth, health) + String.valueOf(formattedHealth) + " ❤" + ChatColor.GRAY + " | " + name;
    }

}
