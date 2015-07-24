package net.thelightmc.minigames.game;

import lombok.Getter;
import lombok.Setter;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.map.Map;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.function.Consumer;

public abstract class GameModule {
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
    }

    public void removePlayer(GamePlayer gamePlayer) {
        new Spectator(gamePlayer);
    }
}
