package net.thelightmc.minigames.timers;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.game.GameModule;

public class GameTimer implements Runnable {
    @Override
    public void run() {
        GameModule module = Minigames.getMinigames().getGameModule();
        if (module != null) {
            return;
        }
    }
}
