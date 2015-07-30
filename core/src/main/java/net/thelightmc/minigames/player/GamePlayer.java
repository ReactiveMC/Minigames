package net.thelightmc.minigames.player;

import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.team.GameTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class GamePlayer {
    private final UUID uuid;
    private GameModule game;
    private boolean editing;
    private final String name;
    private GameTeam team;

    public GamePlayer(UUID uuid) {
        this.uuid = uuid;
        name = getPlayer().get().getName();
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
        if (game == null) {return;}
        game.removePlayer(this);
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public boolean isEditing() {
        return editing;
    }

    public String getName() {
        return name;
    }

    public void setTeam(GameTeam team) {
        this.team = team;
    }

    public GameTeam getTeam() {
        return team;
    }
}
