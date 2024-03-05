package me.josielcm.jcm;

import me.josielcm.jcm.api.Base;
import me.josielcm.jcm.api.BaseAPI;
import me.josielcm.jcm.api.commands.AbstractCommand;
import me.josielcm.jcm.api.commands.CommandManager;
import me.josielcm.jcm.commands.MainCommand;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    @Getter
    private FileConfiguration configFileCon = null;

    File configFile = null;


    @Override
    public void onLoad() {
        BaseAPI.onLoad(this);
        getLogger().warning("Loading plugin please wait...");
    }
    @Override
    public void onEnable() {
        BaseAPI.onEnable();

        Base base = BaseAPI.get();

        CommandManager commandManager = base.getCommandManager();

        AbstractCommand mainCommand = new MainCommand(this);
        commandManager.setMainCommand(mainCommand);
        commandManager.registerCommand(mainCommand);

        getLogger().warning("Plugin enabled successful.");

        createFile();
    }

    private void createFile(){
        configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            this.saveResource("config.yml", false);
        }
        configFileCon = new YamlConfiguration();
        try {
            configFileCon.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            this.getLogger().warning("Ocurrio un error creando o cargando " + configFile.getName() +  ": " + e.getMessage());
        }
    }


    @Override
    public void onDisable() {
        getLogger().warning("Plugin disabled successful.");
        BaseAPI.onDisable();
    }
}
