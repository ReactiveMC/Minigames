package net.thelightmc.minigames.timers;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.Minigame;
import net.thelightmc.minigames.utils.ColorScroller;

public class ScoreboardTimer implements Runnable {
    private final ColorScroller colorScroller = new ColorScroller(Language.SCOREBOARD_TITLE.getMsg());
    @Override
    public void run() {
        Minigame module = Minigames.getMinigames().getMinigame();
        if (module == null) {
            return;
        }
        module.getGameModule().getScoreboard().setTitle(colorScroller.next());
    }
}
