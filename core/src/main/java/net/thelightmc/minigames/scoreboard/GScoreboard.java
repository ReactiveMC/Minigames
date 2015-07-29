package net.thelightmc.minigames.scoreboard;

import net.thelightmc.minigames.scoreboard.label.BasicLabel;
import net.thelightmc.minigames.scoreboard.label.BlankLabel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.YELLOW;

public final class GScoreboard {
    private final Scoreboard scoreboard;
    private final HashMap<Integer,ScoreboardLabel> scoreLabels = new HashMap<>();

    protected GScoreboard() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboard.registerNewObjective("side","dummy");
        scoreboard.getObjective("side").setDisplaySlot(DisplaySlot.SIDEBAR);
    }
    public void setTitle(String name) {
        scoreboard.getObjective(DisplaySlot.SIDEBAR).setDisplayName(
                ChatColor.translateAlternateColorCodes('&', name));
    }
    public void addLabel(ScoreboardLabel label) {
        scoreLabels.put(label.getPosition(),label);
        scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(label.getText())
                .setScore(label.getPosition());
    }
    public ScoreboardLabel getLabel(int pos) {
        return scoreLabels.get(pos);
    }
    public void removeLabel(ScoreboardLabel label) {
        scoreboard.resetScores(label.getText());
        scoreLabels.remove(label.getPosition());
    }

    public void updateLabel(ScoreboardLabel label) {
        if (scoreLabels.containsKey(label.getPosition())) {
            removeLabel(label);
        }
        addLabel(label);
    }
    public ScoreboardLabel getLabel(String s) {
        for (ScoreboardLabel scoreboardLabel : scoreLabels.values()) {
            if (scoreboardLabel.getText().equalsIgnoreCase(s)) {
                return scoreboardLabel;
            }
        }
        return null;
    }
    public void send(Player player) {
        player.setScoreboard(scoreboard);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
