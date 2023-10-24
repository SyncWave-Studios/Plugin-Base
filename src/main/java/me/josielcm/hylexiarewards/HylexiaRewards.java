package me.josielcm.hylexiarewards;

import lombok.Getter;
import me.josielcm.hylexiarewards.api.Base;
import me.josielcm.hylexiarewards.api.BaseAPI;
import me.josielcm.hylexiarewards.api.commands.AbstractCommand;
import me.josielcm.hylexiarewards.api.commands.CommandManager;
import me.josielcm.hylexiarewards.commands.Reward;
import me.josielcm.hylexiarewards.rewards.RedeemRewards;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class HylexiaRewards extends JavaPlugin {

    @Getter
    private FileConfiguration rewards = null;

    File rewardsFile = null;

    public RedeemRewards redeemRewards = new RedeemRewards(this);


    @Override
    public void onLoad() {
        BaseAPI.onLoad(this);
    }
    @Override
    public void onEnable() {
        BaseAPI.onEnable();

        Base base = BaseAPI.get();

        CommandManager commandManager = base.getCommandManager();

        AbstractCommand mainCommand= new Reward(this);
        commandManager.setMainCommand(mainCommand);
        commandManager.registerCommand(mainCommand);

        createFile();
    }

    private void createFile(){
        rewardsFile = new File(this.getDataFolder(), "rewards.yml");
        if (!rewardsFile.exists()) {
            rewardsFile.getParentFile().mkdirs();
            this.saveResource("rewards.yml", false);
        }
        rewards = new YamlConfiguration();
        try {
            rewards.load(rewardsFile);
        } catch (IOException | InvalidConfigurationException e) {
            this.getLogger().warning("Ocurrio un error creando o cargando " + rewardsFile.getName() +  ": " + e.getMessage());
        }
    }


    @Override
    public void onDisable() {
        BaseAPI.onDisable();
    }
}
