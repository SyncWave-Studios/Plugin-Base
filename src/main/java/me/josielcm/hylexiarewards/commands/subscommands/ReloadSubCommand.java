package me.josielcm.hylexiarewards.commands.subscommands;

import me.josielcm.hylexiarewards.HylexiaRewards;
import me.josielcm.hylexiarewards.api.Settings;
import me.josielcm.hylexiarewards.api.commands.AbstractCommand;
import me.josielcm.hylexiarewards.api.commands.CommandHandler;
import me.josielcm.hylexiarewards.api.commands.CommandInfo;
import me.josielcm.hylexiarewards.api.commands.TabCompleteHandler;
import me.josielcm.hylexiarewards.api.utils.Colored;

@CommandInfo(
        usage = "/reward reload",
        description = "Reloads the plugin configuration",
        permission = "hylexiarewards.reload"
)
public class ReloadSubCommand extends AbstractCommand {
    private final HylexiaRewards plugin;

    public ReloadSubCommand(HylexiaRewards plugin) {
        super("reload");
        this.plugin = plugin;
    }

    @Override
    public CommandHandler getCommandHandler() {
        return (sender, args) -> {
            if (sender.hasPermission(getClass().getAnnotation(CommandInfo.class).permission())) {
                Settings.reload();
                sender.sendMessage(Colored.simpleparseMiniMessage("<green>Configuración recargada con éxito.</green>"));
                return true;
            }
            return true;
        };
    }

    @Override
    public TabCompleteHandler getTabCompleteHandler() {
        return null;
    }
}
