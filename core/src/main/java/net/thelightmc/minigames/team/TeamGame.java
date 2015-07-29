package net.thelightmc.minigames.team;

import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.player.PlayerRegistery;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class TeamGame extends GameModule {
    private final List<Team> teams;

    protected TeamGame(int teams) {
        super();
        this.teams = new ArrayList<>(teams);
        for (int i = 1; i < teams+1; i++) {
            this.teams.add(new Team(ChatColor.values()[i],getScoreboard().getScoreboard()));
        }
    }

    public List<Team> getTeams() {
        return teams;
    }

    private void assignTeam(GamePlayer player) {
        Team lowest = teams.get(0);
        for (int i = 1; i < teams.size(); i++) {
            if (lowest.getSize() > teams.get(i).getSize()) {
                lowest = teams.get(i);
            }
        }
        lowest.addPlayer(player);
    }
    public abstract Team checkEnd();

    public void setWinner(Team team) {
        Bukkit.broadcastMessage(Language.TEAM_WON.getMsg().replace("{TEAM_NAME}", team.getName()));
        endGame();
    }

    @Override
    public void removePlayer(GamePlayer gamePlayer) {
        super.removePlayer(gamePlayer);
        Team team = gamePlayer.getTeam();
        team.removePlayer(gamePlayer);
        if (checkEnd() != null) {setWinner(checkEnd());}
    }

    @Override
    public void load() {
        super.load();
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).setSpawn(getMap().getSpawnPoints().get(i));
        }
    }

    @Override
    public void startGame() {
        Bukkit.broadcastMessage(Language.GAME_STARTING.getMsg());
        PlayerRegistery.getPlayers().forEach(gamePlayer -> {
            assignTeam(gamePlayer);
            gamePlayer.setGame(this);
            Player p = gamePlayer.getPlayer().get();
            p.setGameMode(GameMode.SURVIVAL);
            p.setHealth(20);
            p.setFoodLevel(20);
            if (gamePlayer.getTeam()==null) {
                p.teleport(getMap().getRandomSpawn());
            } else {
                p.teleport(gamePlayer.getTeam().getSpawn().getLocation());
            }
        });
        sendScoreboard();
        setRunning(true);
    }

    @Override
    public void endGame() {
        super.endGame();
        teams.forEach(Team::destroy);
    }
}
