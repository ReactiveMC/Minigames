package net.thelightmc.minigames.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.function.Consumer;

public abstract class ScoreboardModule {
    public void updateScoreboard(Consumer<? super Scoreboard> action) {
        for (Player player : Bukkit.getOnlinePlayers())
            action.accept(player.getScoreboard());
    }
    public void updateObjective(Consumer<? super Objective> action) {
        updateObjective(DisplaySlot.SIDEBAR,action);
    }
    public void updateObjective(DisplaySlot displaySlot,Consumer<? super Objective> action) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            action.accept(player.getScoreboard().getObjective(displaySlot));
        }
    }
}
