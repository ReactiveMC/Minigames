package net.thelightmc.minigames.timers;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.utils.ColorScroller;

public class ScoreboardTimer implements Runnable {
    private final ColorScroller colorScroller = new ColorScroller(Language.SCOREBOARD_TITLE.getMsg());
    @Override
    public void run() {
        GameModule module = Minigames.getMinigames().getGameModule();
        if (module == null) {
            return;
        }
        module.updateObjective(objective -> objective.setDisplayName(colorScroller.next()));
    }
}
