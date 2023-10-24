package me.josielcm.hylexiarewards.api.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

public class Colored {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static Component replacePlaceholders(String message, HashMap<String, Component> placeholders) {
        Component finalMessage = miniMessage.deserialize(message);

        for (Map.Entry<String, Component> hashmap : placeholders.entrySet()) {
            finalMessage = finalMessage.replaceText(TextReplacementConfig.builder().matchLiteral(hashmap.getKey()).replacement(hashmap.getValue()).build());
        }

        return finalMessage;
    }

    public static Component simpleparseMiniMessage(String message) {
        var miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(message);
    }

    public static @NotNull String simpleStringSerialize(Component message) {
        var miniMessage = MiniMessage.miniMessage();
        return miniMessage.serialize(message);
    }


}
