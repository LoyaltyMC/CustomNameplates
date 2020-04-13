import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class test {
    public void updatePlayerPrefix(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard == null) {
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }

        Team team = scoreboard.getTeam(player.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
        }

        team.setPrefix("test");
        team.addEntry(player.getName());
        player.setScoreboard(scoreboard); //sets every players scoreboard, adding "player"'s name to the tablist and above their character
    }
}
