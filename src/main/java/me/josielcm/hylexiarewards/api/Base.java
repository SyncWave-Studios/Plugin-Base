package me.josielcm.hylexiarewards.api;

import me.josielcm.hylexiarewards.api.commands.CommandManager;
import me.josielcm.hylexiarewards.api.utils.Common;
import me.josielcm.hylexiarewards.api.utils.DExecutor;
import me.josielcm.hylexiarewards.api.utils.reflect.ReflectionUtil;


import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

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
        Settings.reload();
        Lang.reload();
        DExecutor.init(3);

        this.commandManager = new CommandManager();

    }
    protected void disable() {
        this.commandManager = null;

    }

    public void reload() {
        Settings.reload();
        Lang.reload();
    }



    public File getDataFolder() {
        if (dataFolder == null) {
            dataFolder = new File("plugins/"+ plugin.getName());
        }
        return dataFolder;
    }

}
