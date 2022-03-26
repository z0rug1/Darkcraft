package net.troutmc.darkcraft.darkentity.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class zWxIjbtYf4 implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String player = args[0];
        String uuid = args[1];
        String world = args[2];
        DebugCommand.entityListTeleport(Bukkit.getPlayer(player), Bukkit.getWorld(world), UUID.fromString(uuid));
        return true;
    }

}
