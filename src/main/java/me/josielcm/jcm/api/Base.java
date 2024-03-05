package me.josielcm.jcm.api;

import lombok.Getter;
import me.josielcm.jcm.api.commands.CommandManager;
import me.josielcm.jcm.api.utils.Common;
import me.josielcm.jcm.api.utils.DExecutor;
import me.josielcm.jcm.api.utils.reflect.ReflectionUtil;

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
