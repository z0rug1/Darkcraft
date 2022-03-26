package net.troutmc.darkcraft.features.playerconfig;

import org.bukkit.OfflinePlayer;

public class Balance {

    public static double getBalance(OfflinePlayer player) {
        return PlayerConfigManager.getPlayerConfig(player).getDouble("player.balance");
    }

    public static void setBalance(OfflinePlayer player, double balance) {
        PlayerConfigManager.getPlayerConfig(player).setDouble("player.balance", balance);
    }

    public static void addBalance(OfflinePlayer player, double balance) {
        setBalance(player, getBalance(player) + balance);
    }

    public static String getFormattedBalance(OfflinePlayer player) {
        double balance = Math.round(PlayerConfigManager.getPlayerConfig(player).getDouble("player.balance") * 100.0) / 100.0;
        return String.valueOf(balance);
    }

}
