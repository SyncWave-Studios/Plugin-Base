package me.josielcm.hylexiarewards.rewards;

import me.clip.placeholderapi.PlaceholderAPI;
import me.josielcm.hylexiarewards.HylexiaRewards;
import me.josielcm.hylexiarewards.api.utils.Colored;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RedeemRewards {
    private final HylexiaRewards plugin;

    public RedeemRewards(HylexiaRewards plugin) {
        this.plugin = plugin;
    }

    public static HashMap<UUID, List> users = new HashMap<>();

    public void redeem(Rewards.RewardType type, Player player) {
        if (type.equals(Rewards.RewardType.WIN)) {
            executeActionsWin(player);
        } else if (type.equals(Rewards.RewardType.LOSE)) {
            executeLoserActions(player);
        }
    }

    private void executeActionsWin(Player player) {
        FileConfiguration rewards = plugin.getRewards();
        ConfigurationSection winConditions = rewards.getConfigurationSection("rewards.win-game.conditions");
        ConfigurationSection winActions = rewards.getConfigurationSection("rewards.win-game.actions");

        for (String conditionKey : winConditions.getKeys(false)) {
            String condition = winConditions.getString(conditionKey + ".condition");

            String replacedCondition = condition.replace("<player>", player.getName());
            String finalCondition = PlaceholderAPI.setPlaceholders(player, replacedCondition);


            if (evaluarCondicion(finalCondition, player)) {
                String actionKey = winConditions.getString(conditionKey + ".execute");
                List<String> actions = plugin.getRewards().getStringList("rewards.win-game.actions." + actionKey);

                if (!actions.isEmpty()) {

                    for (int i = 0; i < actions.size(); i++) {
                        String action = actions.get(i);
                        String nextAction = actions.get(i + 1);
                        final boolean[] inWait = new boolean[1];

                        if (action.contains("wait: ")){
                            inWait[0] = true;

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ConsoleCommandSender sender = Bukkit.getConsoleSender();
                                    player.sendMessage("Ejecutando comando despues del wait: " + action);
                                    Bukkit.dispatchCommand(sender, nextAction);

                                    actions.remove(action);
                                    actions.remove(nextAction);

                                    inWait[0] = false;
                                }
                            }.runTaskLaterAsynchronously(plugin, Long.parseLong(action.substring(6)));
                        } else {
                            if (!inWait[0]){
                                ConsoleCommandSender sender = Bukkit.getConsoleSender();
                                player.sendMessage("Ejecutando comando: " + action);
                                Bukkit.dispatchCommand(sender, action);

                                actions.remove(action);
                            } else {
                                i = i - 1;
                            }
                        }
                    }

                } else {
                    player.sendMessage("No se encontraron acciones para la condición: " + conditionKey);
                }
                return;
            }


            /*if (evaluarCondicion(finalCondition, player)) {
                String actionKey = winConditions.getString(conditionKey + ".execute");
                List<String> actions = plugin.getRewards().getStringList("rewards.win-game.actions." + actionKey);



                *//*if (!actions.isEmpty()) {

                    for (String action : actions) {

                        action = action.replace("<player>", Colored.simpleStringSerialize(Component.text(player.getName())));
                        action = PlaceholderAPI.setPlaceholders(player, action);

                        player.sendMessage("Ejecutando comando: " + action);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), action);

                    }
                } else {
                    player.sendMessage("No se encontraron acciones para la condición: " + conditionKey);
                }*//*
                return;
            }*/
        }


    }


    private void executeLoserActions(Player p) {
        FileConfiguration rewards = plugin.getRewards();
        List<String> loserActions = rewards.getStringList("rewards.lose-game.actions");

        if (!loserActions.isEmpty()) {
            for (String action : loserActions) {
                action = action.replace("<player>", Colored.simpleStringSerialize(Component.text(p.getName())));
                String actionPlaceholders = PlaceholderAPI.setPlaceholders(p, action);
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), actionPlaceholders);
            }
        }
    }

    public boolean evaluarCondicion(String condition, Player player) {
        try {
            String[] parts = condition.split(" ");
            if (parts.length != 3) {
                return false;
            }

            String variable = parts[0];
            String operador = parts[1];
            double valor = Double.parseDouble(parts[2]);

            String variableValue = PlaceholderAPI.setPlaceholders(player, variable);

            if (operador.equals(">=")) {
                return Double.parseDouble(variableValue) >= valor;
            } else if (operador.equals("==")) {
                return Double.parseDouble(variableValue) == valor;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
