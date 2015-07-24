package net.thelightmc.minigames.game;

import lombok.Getter;
import lombok.Setter;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.map.Map;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.scoreboard.ScoreboardModule;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.Bukkit;

public abstract class GameModule extends ScoreboardModule {
    @Setter @Getter private Map map;
    @Setter @Getter private GameMeta gameMeta;
    public void startGame() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.teleport(map.getSpawn());
            p.sendMessage(Language.GAME_STARTING.toString());
        });
    }
    public void endGame() {
        Spectator.getPlayers().clear();
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.setAllowFlight(false);
            p.teleport(Minigames.getMinigames().getLobby());
        });
        sendScoreboard();
    }

    public void removePlayer(GamePlayer gamePlayer) {
        new Spectator(gamePlayer);
    }
}
