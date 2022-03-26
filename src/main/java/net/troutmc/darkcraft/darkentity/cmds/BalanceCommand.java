package net.troutmc.darkcraft.darkentity.cmds;

import net.troutmc.darkcraft.features.playerconfig.Balance;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                String balance = Balance.getFormattedBalance(player);
                player.sendMessage(ChatColor.YELLOW + "Balance: " + ChatColor.RESET + balance + " coins");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "/balance <player>");
                return false;
            }
        } else if (args.length == 1) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (!target.hasPlayedBefore()) {
                sender.sendMessage(ChatColor.RED + "Could not find player!");
                return false;
            }
            String balance = Balance.getFormattedBalance(target);
            sender.sendMessage(ChatColor.YELLOW + "Balance of " + target.getName() + ": " + ChatColor.RESET + balance + " coins");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "/balance <player>");
            return false;
        }
    }

}
