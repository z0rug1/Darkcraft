package net.troutmc.darkcraft.darkentity.cmds;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.troutmc.darkcraft.worlds.theprison.ThePrison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class DebugCommand implements CommandExecutor {

    private static final String OPTIONS = "prison_entityListSec1, prison_entityListSec2";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "/darkdebug <" + OPTIONS + ">");
            return false;
        }
        String request = args[0];
        debug(sender, request);
        return true;
    }

    private void debug(CommandSender sender, String request) {
        switch (request) {
            case "prison_entityListSec1":
                entityListDebug(sender, Bukkit.getWorld("theprison"), ThePrison.entityListSec1);
                break;
            case "prison_entityListSec2":
                entityListDebug(sender, Bukkit.getWorld("theprison"), ThePrison.entityListSec2);
                break;
        }
    }

    private void entityListDebug(CommandSender sender, World world, ArrayList<LivingEntity> list) {
        int sizeSec2 = list.size();
        if (sizeSec2 <= 4)
            sender.sendMessage("Of good size. (" + sizeSec2 + ")");
        else
            sender.sendMessage(ChatColor.RED + "ABNORMAL SIZE! (" + sizeSec2 + ")");

        sender.sendMessage("");
        for (int i = 0; i < list.size(); i++) {
            TextComponent msg = new TextComponent(ChatColor.YELLOW + String.valueOf(i + 1) + ". " + ChatColor.RESET + list.get(i).getUniqueId());
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/zWxIjbtYf4 " + sender.getName() + " " + list.get(i).getUniqueId() + " " + world.getName()));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + "Click to teleport").create()));
            sender.spigot().sendMessage(msg);
        }
        sender.sendMessage("");
    }

    public static void entityListTeleport(Player player, World world, UUID uuid) {
        LivingEntity entity = null;
        for (LivingEntity e : world.getLivingEntities()) {
            if (e.getUniqueId().toString().equals(uuid.toString())) {
                entity = e;
                break;
            }
        }

        if (entity == null) {
            player.sendMessage(ChatColor.RED + "Couldn't find entity with UUID " + uuid.toString());
            return;
        }

        player.teleport(entity.getLocation());
    }

}
