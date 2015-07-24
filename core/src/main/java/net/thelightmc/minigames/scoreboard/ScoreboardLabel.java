package net.thelightmc.minigames.scoreboard;

public interface ScoreboardLabel {
    void setText(String text);
    String getText();
    void setPosition(int pos);
    int getPosition();
    void update(GScoreboard scoreboard);
}
