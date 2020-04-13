package com.rngservers.customnameplates.nameplate;

import com.rngservers.customnameplates.CustomNameplates;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NameplateManager {
    private CustomNameplates plugin;

    public NameplateManager(CustomNameplates plugin) {
        this.plugin = plugin;
    }

    public void updateNameplateLoop() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    updatePlayerNameplate(player);
                }
            }
        }.runTaskTimer(plugin, 0L, 80L);
    }

    public void updatePlayerNameplate(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard == null) {
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }

        Team team = scoreboard.getTeam(player.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
        }
        team.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.NEVER);

        String prefix = plugin.getConfig().getString("settings.prefix");
        String suffix = plugin.getConfig().getString("settings.suffix");
        if (plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            prefix = PlaceholderAPI.setPlaceholders(player, prefix);
            suffix = PlaceholderAPI.setPlaceholders(player, suffix);
        }
        prefix = prefix.replace("%", "%%")
                .replace("&", "§");
        suffix = suffix.replace("%", "%%")
                .replace("&", "§");
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        String color = prefix.replace(" ", "").substring(prefix.length() - 2);
        team.setColor(ChatColor.getByChar(color));

        team.addEntry(player.getName());
        player.setScoreboard(scoreboard);
    }


}
