package me.josielcm.jcm.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.josielcm.jcm.JosielPL;
import me.josielcm.jcm.api.commands.AbstractCommand;
import me.josielcm.jcm.api.commands.CommandHandler;
import me.josielcm.jcm.api.commands.CommandInfo;
import me.josielcm.jcm.api.commands.TabCompleteHandler;
import me.josielcm.jcm.api.utils.Color;

@CommandInfo(permission = "", usage = "/josielpl", description = "Main command", minArgs = 3)
public class MainCommand extends AbstractCommand {
    private final JosielPL plugin;

    public MainCommand(JosielPL plugin) {
        super("josielpl");
        this.plugin = plugin;
    }

    @Override
    public CommandHandler getCommandHandler() {
        return (sender, args) -> {
            if (sender.hasPermission(getClass().getAnnotation(CommandInfo.class).permission())) {

                sender.sendMessage(Color.parse("Works!"));

                return true;
            }
            return true;
        };
    }

    @Override
    public TabCompleteHandler getTabCompleteHandler() {
        return null;
    }
}
