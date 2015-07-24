package net.thelightmc.minigames.timers;

import net.thelightmc.minigames.Minigame;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.lang.Language;
import org.bukkit.Bukkit;

import java.util.concurrent.ThreadLocalRandom;

public class GameTimer implements Runnable {
    int ctr = 60;
    @Override
    public void run() {
        Minigames minigames = Minigames.getMinigames();
        GameModule module = minigames.getGameModule();
        if (module != null) {
            //This means the game is running and shouldn't be doing anything
            return;
        }
        ctr--;
        if (ctr <= 0) {
            ctr = 60;
            Minigame minigame = minigames.getMinigameList().get(ThreadLocalRandom.current().
                    nextInt(minigames.getMinigameList().size()));
            minigames.startMinigame(minigame);
            return;
        }
        if (ctr % 10 == 0 || ctr <= 10) {
            Bukkit.broadcastMessage(Language.TIMER_REMAINING.getMsg());
        }
    }
}
