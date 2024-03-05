package me.josielcm.jcm.commands;

import me.josielcm.jcm.Main;
import me.josielcm.jcm.api.commands.AbstractCommand;
import me.josielcm.jcm.api.commands.CommandHandler;
import me.josielcm.jcm.api.commands.CommandInfo;
import me.josielcm.jcm.api.commands.TabCompleteHandler;
import me.josielcm.jcm.api.utils.Colored;
import me.josielcm.jcm.commands.subscommands.SubCommand;

@CommandInfo(
        permission = "",
        usage = "/commandtest",
        description = "Main command."
)
public class MainCommand extends AbstractCommand {
    private final Main plugin;

    public MainCommand(Main plugin) {
        super("commandtest");
        this.plugin = plugin;

        addSubCommand(new SubCommand(plugin));
    }

    @Override
    public CommandHandler getCommandHandler() {
        return (sender, args) -> {
            if (sender.hasPermission(getClass().getAnnotation(CommandInfo.class).permission())) {
                if (args.length == 0) {
                    sender.sendMessage(Colored.simpleparseMiniMessage("<green>The command works!</greeen> <gray>|</gray> <green>If you want u can use:</green> <gold>/commandtest subcommand</gold>"));
                    return true;
                }
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
