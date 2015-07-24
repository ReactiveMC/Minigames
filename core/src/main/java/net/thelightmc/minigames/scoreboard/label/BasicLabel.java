package net.thelightmc.minigames.scoreboard.label;

import net.thelightmc.minigames.scoreboard.GScoreboard;
import net.thelightmc.minigames.scoreboard.ScoreboardLabel;
import org.bukkit.ChatColor;

public class BasicLabel implements ScoreboardLabel {
    public BasicLabel(String text,int position) {
        this.text = ChatColor.translateAlternateColorCodes('&',text);
        this.position = position;
    }
    private String text;
    private int position;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
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
