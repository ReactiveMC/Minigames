package net.thelightmc.minigames.scoreboard.label;

import net.thelightmc.minigames.scoreboard.GScoreboard;
import net.thelightmc.minigames.scoreboard.ScoreboardLabel;
import org.bukkit.ChatColor;

public class BlankLabel implements ScoreboardLabel {
    private static int COLOR_ROTATION_VALUE = 0;
    private int position;
    private final ChatColor color;
    public BlankLabel(int position) {
        ChatColor[] values = ChatColor.values();
        color = values[COLOR_ROTATION_VALUE];
        COLOR_ROTATION_VALUE++;
        if (COLOR_ROTATION_VALUE > values.length) {
            COLOR_ROTATION_VALUE = 0;
        }
        this.position = position;
    }
    @Deprecated
    public void setText(String text) {}

    public String getText() {
        return color.toString();
    }

    @Override
    public void setPosition(int pos) {
        this.position = pos;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void update(GScoreboard scoreboard) {
        scoreboard.updateLabel(this);
    }
}
