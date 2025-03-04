package me.josielcm.jcm.api;

import lombok.Getter;
import lombok.Setter;

public class SettingsAPI {

    @Getter
    @Setter
    private static String prefix = "<gold>JPL</gold> ";

    @Getter
    private static final String prefixCommandRegister = "JosielPL";
    
    @Getter
    private static final String usageCMD = prefix + "<gray>Usage:</gray> <white><usage></white>";

    @Getter
    private static final String usageSubCMD = prefix + "<gray>Usage:</gray> <white><usage></white>";

    @Getter
    private static final String noPermission = prefix + "<red>Uh... you don't have the permission required to execute this command.</red>";

    @Getter
    private static final String onlyPlayers = prefix + "<red>Sorry, this command only can execute by players.</red>";

}
