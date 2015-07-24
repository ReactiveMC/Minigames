package net.thelightmc.minigames.player;

import net.thelightmc.minigames.game.GameModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class GamePlayer {
    private final UUID uuid;
    private GameModule game;
    public GamePlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public Optional<Player> getPlayer() {
        Player p = Bukkit.getPlayer(uuid);
        if (p != null) {
            return Optional.of(p);
        }
        return Optional.empty();
    }

    public UUID getUniqueID() {
        return uuid;
    }

    public GameModule getGame() {
        return game;
    }

    public void setGame(GameModule game) {
        this.game = game;
    }

    public void removeFromGame() {
        game.removePlayer(this);
    }
}