package net.thelightmc.minigames.scoreboard;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.function.Consumer;

public abstract class ScoreboardModule {
    @Getter
    private final GScoreboard scoreboard;

    protected ScoreboardModule() {
        scoreboard = new GScoreboard();
    }

    public void updateScoreboard(Consumer<? super GScoreboard> action) {
        action.accept(scoreboard);
    }
    public void sendScoreboard() {
        Bukkit.getOnlinePlayers().forEach(scoreboard::send);
    }
}
