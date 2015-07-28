package net.thelightmc.minigames.tdm;

import net.thelightmc.minigames.game.GameMeta;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.team.Team;
import net.thelightmc.minigames.team.TeamGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

@GameMeta(name = "TDM",description = "Team deathmatch \n" +
        "Kill the other team!")
public class TDMGame extends GameModule implements TeamGame {
    private final Team[] teams;
    public TDMGame() {
        super();
        this.teams = new Team[2];
        teams[0] = new Team(ChatColor.RED, getScoreboard().getScoreboard());
        teams[1] = new Team(ChatColor.BLUE, getScoreboard().getScoreboard());
    }

    @Override
    public void startGame() {
        PlayerRegistery.getPlayers().forEach(this::assignTeam);
        super.startGame();
    }

    @Override
    public Team[] getTeams() {
        return teams;
    }
    private void assignTeam(GamePlayer player) {
        if (teams[0].getPlayers().size() > teams[1].getPlayers().size()) {
            teams[1].addPlayer(player);
            return;
        }
        teams[0].addPlayer(player);
    }
    private void checkEnd() {
        if (teams[0].getPlayers().isEmpty()) {
            setWinner(teams[1]);
            endGame();
        }
        if (teams[1].getPlayers().isEmpty()) {
            setWinner(teams[0]);
            endGame();
        }
    }

    private void setWinner(Team team) {
        Bukkit.broadcastMessage(Language.TEAM_WON.getMsg().replace("{TEAM_NAME}",team.getName()));
    }

    @Override
    public void removePlayer(GamePlayer gamePlayer) {
        super.removePlayer(gamePlayer);
        Team team = gamePlayer.getTeam();
        team.removePlayer(gamePlayer);
        checkEnd();
    }

    @Override
    public void load() {
        super.load();
        teams[0].setSpawn(getMap().getSpawnPoints().get(0));
        teams[1].setSpawn(getMap().getSpawnPoints().get(1));
    }
}
