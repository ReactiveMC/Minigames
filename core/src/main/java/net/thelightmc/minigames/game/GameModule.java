package net.thelightmc.minigames.game;

import lombok.Getter;
import lombok.Setter;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.map.Map;
import net.thelightmc.minigames.map.MapLoader;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.scoreboard.ScoreboardModule;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.Bukkit;

public abstract class GameModule extends ScoreboardModule {
    public GameModule() {
        super();
        setGameMeta(this.getClass().getDeclaredAnnotation(GameMeta.class));
    }
    @Setter @Getter private Map map;
    @Setter @Getter private GameMeta gameMeta;
    public void startGame() {
        map = MapLoader.get().loadRandomMap(gameMeta.name());
        Bukkit.broadcastMessage(Language.GAME_STARTING.getMsg());
        Bukkit.getScheduler().runTaskLater(Minigames.getMinigames().getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> p.teleport(map.getSpawn()));
            }
        }, 20);
        sendScoreboard();
    }
    public void endGame() {
        Spectator.getPlayers().clear();
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.setAllowFlight(false);
            p.teleport(Minigames.getMinigames().getLobby());
        });
    }

    public void removePlayer(GamePlayer gamePlayer) {
        new Spectator(gamePlayer);
    }
}