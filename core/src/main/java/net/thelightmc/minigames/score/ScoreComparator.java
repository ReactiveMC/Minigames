package net.thelightmc.minigames.score;

import java.util.Comparator;
import java.util.Map;

public class ScoreComparator implements Comparator<String> {

    private final Map<String, Integer> base;

    public ScoreComparator(Map<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        return base.get(a) <= base.get(b) ? -1 : 1;
    }
}
