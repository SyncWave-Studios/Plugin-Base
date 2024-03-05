package me.josielcm.jcm.api;

import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;
import me.josielcm.jcm.api.utils.config.*;

import org.bukkit.configuration.ConfigurationSection;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@UtilityClass
public class Settings {

    private static final Base BASE = BaseAPI.get();
    private static final FileConfig CONFIG = new FileConfig(BASE.getPlugin(), "rewards.yml");

    @Key("update-checker")
    public static boolean CHECK_FOR_UPDATES = false;


    // ========================================= //

    /**
     * Reload all Settings
     */
    public static void reload() {
        CONFIG.reload();

        CFG.load(BASE.getPlugin(), Settings.class, CONFIG.getFile());

        // -- Load custom replacements
        ConfigurationSection customReplacementsSection = CONFIG.getConfigurationSection("custom-replacements");
        if (customReplacementsSection != null) {
            Map<String, String> replacements = new HashMap<>();
            for (String key : customReplacementsSection.getKeys(false)) {
                if (!customReplacementsSection.isString(key)) {
                    continue;
                }
                replacements.put(key, customReplacementsSection.getString(key));
            }
        }
    }

    public static FileConfig getConfig() {
        return CONFIG;
    }
}