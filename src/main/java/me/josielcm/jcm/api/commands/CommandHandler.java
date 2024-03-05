package me.josielcm.jcm.api.commands;

import org.bukkit.command.CommandSender;

import me.josielcm.jcm.api.exception.AbstractCommandException;

@FunctionalInterface
public interface CommandHandler {
    boolean handle(CommandSender sender, String[] args) throws AbstractCommandException, InterruptedException;
}
