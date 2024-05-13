package me.josielcm.jcm.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.josielcm.jcm.MeteorSApply;
import me.josielcm.jcm.api.commands.AbstractCommand;
import me.josielcm.jcm.api.commands.CommandHandler;
import me.josielcm.jcm.api.commands.CommandInfo;
import me.josielcm.jcm.api.commands.TabCompleteHandler;
import me.josielcm.jcm.commands.subscommands.SubCommand;

@CommandInfo(permission = "", usage = "/cooldown", description = "Apply cooldown to player material item.", minArgs = 3)
public class MainCommand extends AbstractCommand {
    private final MeteorSApply plugin;

    public MainCommand(MeteorSApply plugin) {
        super("cooldown");
        this.plugin = plugin;
    }

    @Override
    public CommandHandler getCommandHandler() {
        return (sender, args) -> {
            if (sender.hasPermission(getClass().getAnnotation(CommandInfo.class).permission())) {

                String material = args[0];
                String timInTicks = args[1];
                String playerName = args[2];

                Player target = Bukkit.getPlayer(playerName);

                if (target != null) {
                    Material mat = Material.valueOf(material.toUpperCase());

                    if (mat != null) {
                        if (Integer.valueOf(timInTicks) != null) {
                            target.setCooldown(mat, Integer.valueOf(timInTicks));
                            sender.sendMessage("Cooldown aplicado para: " + playerName + " | Material: " + material
                                    + " | Tiempo en ticks: " + Integer.valueOf(timInTicks));
                        } else {
                            sender.sendMessage("El tiempo en ticks es invalido");
                        }
                    } else {
                        sender.sendMessage("Material no encontrado");
                    }
                } else {
                    sender.sendMessage("Jugador " + playerName + " no encontrado");
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
