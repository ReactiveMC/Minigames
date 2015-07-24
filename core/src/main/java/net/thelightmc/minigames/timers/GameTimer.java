package net.thelightmc.minigames.timers;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.scoreboard.ScoreboardModule;
import org.bukkit.OfflinePlayer;

public class GameTimer implements Runnable {
    int ctr = 60;
    @Override
    public void run() {
        GameModule module = Minigames.getMinigames().getGameModule();
        if (module != null) {
            return;
        }
    }
}
