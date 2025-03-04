package me.josielcm.jcm.utils;

import java.util.logging.Logger;

import me.josielcm.jcm.api.utils.RandomUtils;

public class Logs {
    
    public static void onLoad(Logger logger) {
        logger.info("");
        logger.info(RandomUtils.asciiArt(" JosielPL"));
        logger.info("");
        logger.info("Loading JosielPL please wait...");
        logger.info("");
    }

    public static void onEnable(Logger logger) {
        logger.info("");
        logger.info(RandomUtils.asciiArt(" JosielPL"));
        logger.info("");
        logger.info("JosielPL enabled successfully!");
        logger.info("");
    }

    public static void onDisable(Logger logger) {
        logger.info("");
        logger.info(RandomUtils.asciiArt(" JosielPL"));
        logger.info("");
        logger.info("JosielPL disabled successfully.");
        logger.info("");
    }

}
