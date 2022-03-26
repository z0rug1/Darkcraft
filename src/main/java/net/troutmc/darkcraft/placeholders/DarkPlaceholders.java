package net.troutmc.darkcraft.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.troutmc.darkcraft.features.playerconfig.Balance;
import net.troutmc.darkcraft.features.playerconfig.PlayerConfig;
import net.troutmc.darkcraft.features.playerconfig.PlayerConfigManager;
import org.bukkit.OfflinePlayer;

public class DarkPlaceholders extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "LeonTheDev";
    }

    @Override
    public String getIdentifier() {
        return "darkcraft";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (player == null) return null;
        if (params == null) return null;
        PlayerConfig playerConfig = PlayerConfigManager.getPlayerConfig(player);

        switch (params) {
            case "balance_user":
                return Balance.getFormattedBalance(player);
        }

        return null;
    }

}
