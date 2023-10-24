package me.josielcm.hylexiarewards.api.utils;

import me.josielcm.hylexiarewards.api.utils.color.IridiumColorAPI;
import me.josielcm.hylexiarewards.api.utils.reflect.ReflectionUtil;
import me.josielcm.hylexiarewards.api.utils.reflect.Version;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;
@UtilityClass
public class Common {

	private static final Pattern SPACING_CHARS_REGEX = Pattern.compile("[_ \\-]+");
	public static final Version SERVER_VERSION = Version.valueOf(ReflectionUtil.getVersion());
	public static String PREFIX = "&8[&2HylexiaRewards&8] &7";

	/*
	 * 	Colorize
	 */

	public static String colorize(String string) {
		return IridiumColorAPI.process(string);
	}

	public static List<String> colorize(List<String> list) {
		list.replaceAll(Common::colorize);
		return list;
	}

	public static String stripColors(String string) {
		return ChatColor.stripColor(IridiumColorAPI.stripColorFormatting(string));
	}

	/*
	 * 	Log
	 */

	/**
	 * Log a message into console.
	 *
	 * @param message The message.
	 */
	public static void log(String message) {
		log(Level.INFO, message);
	}

	/**
	 * Log a message into console.
	 * <p>
	 *     This method formats given arguments in the message.
	 * </p>
	 *
	 * @param message The message.
	 * @param args The arguments
	 */
	public static void log(String message, Object... args) {
		log(String.format(message, args));
	}

	/**
	 * Log a message into console.
	 *
	 * @param level Level of this message.
	 * @param message The message.
	 */
	public static void log(Level level, String message) {
		Bukkit.getServer().getLogger().log(level, "[HylexiaReward] " + message);
	}

	/**
	 * Log a message into console.
	 * <p>
	 *     This method formats given arguments in the message.
	 * </p>
	 *
	 * @param level Level of this message.
	 * @param message The message.
	 * @param args The arguments.
	 */
	public static void log(Level level, String message, Object... args) {
		log(level, String.format(message, args));
	}

	/*
	 * 	Debug
	 */

	/**
	 * Print an object into console.
	 *
	 * @param o Object to print.
	 */
	public static void debug(Object o) {
		System.out.println(o);
	}

	/*
	 * 	Tell
	 */

	/**
	 * Send a message to given CommandSender.
	 * <p>
	 *     This method will colorize the message.
	 * </p>
	 *
	 * @param player The CommandSender receiving the message.
	 * @param message The message.
	 */
	public static void tell(CommandSender player, String message) {
		player.sendMessage(colorize(message));
	}

	/**
	 * Send a message to given CommandSender.
	 * <p>
	 *     This method will colorize the message and formats given arguments to the message.
	 * </p>
	 *
	 * @param player The CommandSender receiving the message.
	 * @param message The message.
	 * @param args The arguments.
	 */
	public static void tell(CommandSender player, String message, Object... args) {
		tell(player, String.format(message, args));
	}

	public static String removeSpacingChars(String string) {
		return SPACING_CHARS_REGEX.matcher(string).replaceAll("");
	}

}