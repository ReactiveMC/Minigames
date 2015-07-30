package net.thelightmc.minigames.game;

import lombok.Getter;
import lombok.Setter;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.map.Map;
import net.thelightmc.minigames.map.MapLoader;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.scoreboard.ScoreboardLabel;
import net.thelightmc.minigames.scoreboard.label.BasicLabel;
import net.thelightmc.minigames.scoreboard.label.BlankLabel;
import net.thelightmc.minigames.spectator.Spectator;
import net.thelightmc.minigames.scoreboard.ScoreboardModule;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.YELLOW;


public abstract class GameModule extends ScoreboardModule {
    public GameModule() {
        super();
        setGameMeta(this.getClass().getDeclaredAnnotation(GameMeta.class));
        getScoreboard().setTitle(ChatColor.GREEN + ChatColor.BOLD.toString() + getGameMeta().name());
        getScoreboard().addLabel(new BasicLabel(AQUA + BOLD.toString() + "Game",15));
        getScoreboard().addLabel(new BasicLabel(gameMeta.name(),14));
        getScoreboard().addLabel(new BlankLabel(13));
        getScoreboard().addLabel(new BasicLabel(YELLOW + BOLD.toString() + "Map", 12));
        getScoreboard().addLabel(new BasicLabel("Map loading...", 11));
    }
    @Setter @Getter private Map map;
    @Setter @Getter private GameMeta gameMeta;
    @Setter @Getter private boolean running;
    public void startGame() {
        Bukkit.broadcastMessage(Language.GAME_STARTING.getMsg());
        PlayerRegistery.getPlayers().forEach(gamePlayer -> {
            gamePlayer.setGame(this);
            Player p = gamePlayer.getPlayer().get();
            p.setGameMode(GameMode.SURVIVAL);
            p.setHealth(20);
            p.setFoodLevel(20);
            if (gamePlayer.getTeam()==null) {
                p.teleport(map.getRandomSpawn());
            } else {
                p.teleport(gamePlayer.getTeam().getSpawn().getLocation());
            }
        });
        sendScoreboard();
        setRunning(true);
    }
    public void endGame() {
        Spectator.getPlayers().clear();
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.setAllowFlight(false);
            p.teleport(Minigames.getMinigames().getLobby());
            p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            p.getInventory().clear();
            Bukkit.getOnlinePlayers().forEach(oPlayer -> oPlayer.showPlayer(p)); //Ew but not sure how else to do it D:
        });
        PlayerRegistery.getPlayers().forEach(g -> {
            g.setGame(null);
            g.setTeam(null);
        });
        Minigames.getMinigames().getMinigame().getGameListener().disable();
        Minigames.getMinigames().setMinigame(null);
        setRunning(false);
    }

    public void removePlayer(GamePlayer gamePlayer) {}

    public void load() {
        map = MapLoader.get().loadRandomMap(gameMeta.name());
        ScoreboardLabel label = getScoreboard().getLabel(11);
        getScoreboard().removeLabel(label);
        label.setText(getMap().getName());
        getScoreboard().addLabel(label);
    }
}