package net.thelightmc.minigames.team;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.utils.GameLocation;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

public final class TeamBuilder {
    private final String name;
    private String prefix;
    private Scoreboard scoreboard;
    private GameLocation spawn;
    private boolean friendlyFire;
    private int max;

    public TeamBuilder(String name) {
        this.name = name;
        spawn = Minigames.getMinigames().getMinigame().getGameModule().getMap().getSpawnPoints().get(0);
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        prefix = "";
        friendlyFire = false;
        max = Integer.MAX_VALUE;
    }

    public GameTeam build() {
        return new GameTeam(name,prefix,scoreboard,spawn,friendlyFire, max);
    }

    public TeamBuilder prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
    public TeamBuilder scorebaord(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        return this;
    }

    public TeamBuilder spawn(GameLocation gameLocation) {
        this.spawn = gameLocation;
        return this;
    }

    public TeamBuilder friendlyFire(boolean friendlyFire) {
        this.friendlyFire = friendlyFire;
        return this;
    }

    public TeamBuilder max(int max) {
        this.max = max;
        return this;
    }
}
