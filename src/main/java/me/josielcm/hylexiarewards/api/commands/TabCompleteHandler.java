package me.josielcm.hylexiarewards.api.commands;

import me.josielcm.hylexiarewards.api.Base;
import me.josielcm.hylexiarewards.api.BaseAPI;
import org.bukkit.command.CommandSender;

import java.util.*;

@FunctionalInterface
public interface TabCompleteHandler {
    Base PLUGIN = BaseAPI.get();

    static List<String> getPartialMatches(String token, String... originals) {
        return getPartialMatches(token, Arrays.asList(originals));
    }

    static List<String> getPartialMatches(String token, Collection<String> originals) {
        if (originals == null) {
            return Collections.emptyList();
        }

        if (token == null || token.isEmpty()) {
            return new ArrayList<>(originals);
        }

        List<String> matches = new ArrayList<>();
        for (String str : originals) {
            if (str.length() >= token.length() && str.regionMatches(true, 0, token, 0, token.length())) {
                matches.add(str);
            }
        }

        return matches;
    }

    /**
     * Handle Tab Complete.
     *
     * @param sender The sender.
     * @param args The arguments.
     * @return List of Tab Completed strings.
     */
    List<String> handleTabComplete(CommandSender sender, String[] args);
}
