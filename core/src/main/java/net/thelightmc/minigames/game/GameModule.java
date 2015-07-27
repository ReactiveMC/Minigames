package net.thelightmc.minigames.game;

import lombok.Getter;
import lombok.Setter;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.map.Map;
import net.thelightmc.minigames.map.MapLoader;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.scoreboard.ScoreboardModule;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

public abstract class GameModule extends ScoreboardModule {
    public GameModule() {
        super();
        setGameMeta(this.getClass().getDeclaredAnnotation(GameMeta.class));
    }
    @Setter @Getter private Map map;
    @Setter @Getter private GameMeta gameMeta;
    @Setter @Getter private boolean running;
    public void startGame() {
        Bukkit.broadcastMessage(Language.GAME_STARTING.getMsg());
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.teleport(map.getSpawn());
            p.setGameMode(GameMode.SURVIVAL);
            p.setHealth(20);
            p.setSaturation(20);
        });
        PlayerRegistery.getPlayers().forEach(gamePlayer -> gamePlayer.setGame(this));
        sendScoreboard();
    }
    public void endGame() {
        Spectator.getPlayers().clear();
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.setAllowFlight(false);
            p.teleport(Minigames.getMinigames().getLobby());
            p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            Bukkit.getOnlinePlayers().forEach(oPlayer -> oPlayer.showPlayer(p)); //Ew but not sure how else to do it D:
        });
        PlayerRegistery.getPlayers().forEach(g -> g.setGame(null));
        Minigames.getMinigames().getMinigame().getGameListener().disable();
        Minigames.getMinigames().setMinigame(null);
    }

    public abstract void removePlayer(GamePlayer gamePlayer);

    public void load() {
        map = MapLoader.get().loadRandomMap(gameMeta.name());
    }
}