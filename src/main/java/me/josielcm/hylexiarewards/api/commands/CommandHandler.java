package me.josielcm.hylexiarewards.api.commands;

import me.josielcm.hylexiarewards.api.exception.AbstractCommandException;
import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface CommandHandler {
    boolean handle(CommandSender sender, String[] args) throws AbstractCommandException, InterruptedException;
}
