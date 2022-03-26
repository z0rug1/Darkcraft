package net.troutmc.darkcraft.darkentity.cmds;

import net.troutmc.darkcraft.darkentity.entities.DarkEntity;
import net.troutmc.darkcraft.darkentity.entities.skeletonguard.SkeletonGuard;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.troutmc.darkcraft.Messages.*;

public class DarkEntitySummon implements CommandExecutor {

    private static final String ENTITY_LIST = "SKELETON_GUARD";
    private static final String INVALID_ARGS = ChatColor.RED + "Invalid syntax! Usage: /darkentitysummon [" + ENTITY_LIST + "]";
    private static final String ENTITY_INVALID = ChatColor.RED + "{name} isn't a valid dark entity!";
    private static final String ENTITY_SUMMONED = "Summoned dark entity {name}";

    private final SkeletonGuard skeletonGuard = new SkeletonGuard();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(SENDER_NOT_PLAYER);
            return false;
        }

        Player player = (Player)sender;

        if (args.length != 1) {
            player.sendMessage(INVALID_ARGS);
            return false;
        }

        entitySummon(player, args[0]);

        return true;
    }

    private void entitySummon(Player player, String arg0) {
        switch (arg0) {
            case "SKELETON_GUARD":
                entitySummon(player, arg0, skeletonGuard);
                break;
            default:
                player.sendMessage((ENTITY_INVALID).replaceAll("\\{name\\}", arg0));
                break;
        }
    }

    private void entitySummon(Player player, String name, DarkEntity darkEntity) {
        darkEntity.summon(player.getLocation());
        entitySummonedMsg(player, name);
    }

    private void entitySummonedMsg(Player player, String name) {
        player.sendMessage((ENTITY_SUMMONED).replaceAll("\\{name\\}", name));
    }

}
