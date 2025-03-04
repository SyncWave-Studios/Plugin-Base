package me.josielcm.jcm.api.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

public class Color {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static Component placeholders(String message, HashMap<String, Component> placeholders) {
        Component finalMessage = miniMessage.deserialize(message);

        for (Map.Entry<String, Component> hashmap : placeholders.entrySet()) {
            finalMessage = finalMessage.replaceText(TextReplacementConfig.builder().matchLiteral(hashmap.getKey()).replacement(hashmap.getValue()).build());
        }

        return finalMessage;
    }

    public static Component parse(String message) { return miniMessage.deserialize(message); }

    public static @NotNull String parse(Component message) { return miniMessage.serialize(message); }
}
