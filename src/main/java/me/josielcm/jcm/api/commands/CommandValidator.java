package me.josielcm.jcm.api.commands;

import lombok.experimental.UtilityClass;
import me.josielcm.jcm.api.SettingsAPI;
import me.josielcm.jcm.api.utils.Color;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@UtilityClass
public class CommandValidator {

    /**
     * Check if String is a valid identifier of Command.
     *
     * @param identifier  The String to check.
     * @param commandBase The Command.
     * @return Boolean whether the String is a valid identifier of the CommandBase.
     */
    public static boolean isIdentifier(String identifier, CommandBase commandBase) {
        if (identifier.equalsIgnoreCase(commandBase.getName())) {
            return true;
        }

        for (String alias : commandBase.getAliases()) {
            if (identifier.equalsIgnoreCase(alias)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if CommandSender is allowed to execute Command.
     * <p>
     * If the CommandSender isn't allowed to execute the Command, message
     * will be sent to him.
     * </p>
     *
     * @param sender      The CommandSender
     * @param commandBase The Command being executed
     * @return Boolean whether the Command may be executed by the CommandSender
     */
    public static boolean canExecute(CommandSender sender, CommandBase commandBase) {
        if (commandBase.isPlayerOnly() && !(sender instanceof Player)) {
            sender.sendMessage(Color.parse(SettingsAPI.getOnlyPlayers()));
            return false;
        }

        String perm = commandBase.getPermission();
        if (perm != null && !perm.trim().isEmpty() && !sender.hasPermission(perm)) {
            sender.sendMessage(Color.parse(SettingsAPI.getNoPermission()));
            return false;
        }
        return true;
    }

    public static int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

}