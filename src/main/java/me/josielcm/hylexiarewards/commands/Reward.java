package me.josielcm.hylexiarewards.commands;

import me.josielcm.hylexiarewards.HylexiaRewards;
import me.josielcm.hylexiarewards.api.commands.AbstractCommand;
import me.josielcm.hylexiarewards.api.commands.CommandHandler;
import me.josielcm.hylexiarewards.api.commands.CommandInfo;
import me.josielcm.hylexiarewards.api.commands.TabCompleteHandler;
import me.josielcm.hylexiarewards.api.utils.Colored;
import me.josielcm.hylexiarewards.commands.subscommands.LoseGame;
import me.josielcm.hylexiarewards.commands.subscommands.ReloadSubCommand;
import me.josielcm.hylexiarewards.commands.subscommands.WinGame;

@CommandInfo(
        permission = "hylexiarewards.command.reward",
        usage = "/reward",
        description = "Reward command"
)
public class Reward extends AbstractCommand {
    private final HylexiaRewards plugin;
    public Reward(HylexiaRewards plugin) {
        super("reward");
        this.plugin = plugin;

        addSubCommand(new WinGame(plugin));
        addSubCommand(new LoseGame(plugin));
        addSubCommand(new ReloadSubCommand(plugin));
    }

    @Override
    public CommandHandler getCommandHandler() {
        return (sender, args) -> {
            if (sender.hasPermission(getClass().getAnnotation(CommandInfo.class).permission())) {
                if (args.length == 0) {
                    sender.sendMessage(Colored.simpleparseMiniMessage("<red>Usa</red> <gold>/reward <type> <player></gold>"));
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
