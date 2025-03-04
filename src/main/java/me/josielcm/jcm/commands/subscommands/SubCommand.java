package me.josielcm.jcm.commands.subscommands;

import net.kyori.adventure.text.Component;

import me.josielcm.jcm.JosielPL;
import me.josielcm.jcm.api.commands.AbstractCommand;
import me.josielcm.jcm.api.commands.CommandHandler;
import me.josielcm.jcm.api.commands.CommandInfo;
import me.josielcm.jcm.api.commands.TabCompleteHandler;

@CommandInfo(
        permission = "",
        usage = "/commandtest",
        description = "Subcommand of the main command"
)
public class SubCommand extends AbstractCommand {
    private final JosielPL plugin;

    public SubCommand(JosielPL plugin) {
        super("subcommand");
        this.plugin = plugin;
    }

    @Override
    public CommandHandler getCommandHandler() {
        return (sender, args) -> {
            if (sender.hasPermission(getClass().getAnnotation(CommandInfo.class).permission())) {
                sender.sendMessage(Component.text("SubCommand works"));

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
