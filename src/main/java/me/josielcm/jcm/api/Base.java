package me.josielcm.jcm.api;

import lombok.Getter;
import me.josielcm.jcm.api.commands.CommandManager;
import org.apache.commons.lang3.Validate;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public class Base {
    private final JavaPlugin plugin;

    private CommandManager commandManager;


    private File dataFolder;
    private boolean updateAvailable;


    Base(JavaPlugin plugin){
        Validate.notNull(plugin);
        this.plugin=plugin;
    }
    protected void enable() {
        this.commandManager = new CommandManager();
    }
    protected void disable() {
        this.commandManager = null;

    }
}
