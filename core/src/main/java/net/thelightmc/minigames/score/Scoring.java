package net.thelightmc.minigames.score;

import net.thelightmc.minigames.player.GamePlayer;

import java.util.Map;

public interface Scoring {
    int getScore(GamePlayer gamePlayer);
    void incrementScore(GamePlayer gamePlayer);
    void setScore(GamePlayer gamePlayer, int score);
    Map<String,Integer> sortScores();
    int getScoreCap();
}
