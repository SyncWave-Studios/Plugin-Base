package me.josielcm.hylexiarewards.commands.subscommands;

import me.josielcm.hylexiarewards.HylexiaRewards;
import me.josielcm.hylexiarewards.api.commands.AbstractCommand;
import me.josielcm.hylexiarewards.api.commands.CommandHandler;
import me.josielcm.hylexiarewards.api.commands.CommandInfo;
import me.josielcm.hylexiarewards.api.commands.TabCompleteHandler;
import me.josielcm.hylexiarewards.api.utils.Colored;
import me.josielcm.hylexiarewards.rewards.Rewards;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

@CommandInfo(
        permission = "",
        usage = "/reward",
        description = "Reward command"
)
public class WinGame extends AbstractCommand {
    private final HylexiaRewards plugin;

    public WinGame(HylexiaRewards plugin) {
        super("win");
        this.plugin = plugin;
    }

    @Override
    public CommandHandler getCommandHandler() {
        return (sender, args) -> {
            if (sender.hasPermission(getClass().getAnnotation(CommandInfo.class).permission())) {
                if (args.length == 0) {
                    sender.sendMessage(Colored.simpleparseMiniMessage("<red>Usa</red> <gold>/reward <type> <player></gold>"));
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[0]);

                if (target == null) {
                    sender.sendMessage(Colored.simpleparseMiniMessage("<red>Jugador no encontrado. Intenta de nuevo.</red>"));
                    return true;
                }

                HashMap<String, Component> replaced = new HashMap<>();
                replaced.put("<player>", target.displayName());

                sender.sendMessage(Colored.replacePlaceholders(plugin.getRewards().getString("messages.execute"), replaced));
                plugin.redeemRewards.redeem(Rewards.RewardType.WIN, target);

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
