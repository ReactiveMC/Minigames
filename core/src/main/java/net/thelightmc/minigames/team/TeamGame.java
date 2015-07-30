package net.thelightmc.minigames.team;

import net.thelightmc.minigames.exceptions.GameException;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.player.PlayerRegistery;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class TeamGame extends GameModule {
    private final List<GameTeam> teams;

    protected TeamGame() {
        super();
        this.teams = new ArrayList<>();
    }

    public List<GameTeam> getTeams() {
        return teams;
    }

    private void assignTeam(GamePlayer player) {
        GameTeam lowest = null;
        for (GameTeam team : teams) {
            if (lowest == null || lowest.getSize() > team.getSize()) {
                lowest = team;
            }
        }
        if (lowest == null) {
            try {
                throw new GameException("You need to register teams for a team game.");
            } catch (GameException e) {
                e.printStackTrace();
                return;
            }
        }
        lowest.addPlayer(player);
    }
    public abstract GameTeam checkEnd();

    public void setWinner(GameTeam team) {
        Bukkit.broadcastMessage(Language.TEAM_WON.getMsg().replace("{TEAM_NAME}", team.getName()));
        endGame();
    }

    @Override
    public void removePlayer(GamePlayer gamePlayer) {
        super.removePlayer(gamePlayer);
        GameTeam team = gamePlayer.getTeam();
        team.removePlayer(gamePlayer);
        if (checkEnd() != null) {setWinner(checkEnd());}
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
        teams.forEach(GameTeam::destroy);
    }
}
