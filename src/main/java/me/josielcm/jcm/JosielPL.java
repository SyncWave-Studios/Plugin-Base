package me.josielcm.jcm;

import me.josielcm.jcm.api.Base;
import me.josielcm.jcm.api.BaseAPI;
import me.josielcm.jcm.api.commands.AbstractCommand;
import me.josielcm.jcm.api.commands.CommandManager;
import me.josielcm.jcm.commands.MainCommand;
import me.josielcm.jcm.utils.Logs;

import org.bukkit.plugin.java.JavaPlugin;

public final class JosielPL extends JavaPlugin {

    @Override
    public void onLoad() {
        Logs.onLoad(getLogger());
        BaseAPI.onLoad(this);
    }

    @Override
    public void onEnable() {
        BaseAPI.onEnable();
        registerCommands();

        Logs.onEnable(getLogger());
    }

    private void registerCommands() {
        Base base = BaseAPI.get();

        CommandManager commandManager = base.getCommandManager();

        AbstractCommand mainCommand = new MainCommand(this);
        commandManager.setMainCommand(mainCommand);
        commandManager.registerCommand(mainCommand);
    }

    @Override
    public void onDisable() {
        Logs.onDisable(getLogger());
        BaseAPI.onDisable();
    }
}
