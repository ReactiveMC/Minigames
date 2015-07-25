package net.thelightmc.minigames.timers;

import lombok.Setter;
import net.thelightmc.minigames.Minigame;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.lang.Language;
import org.bukkit.Bukkit;

public class GameTimer implements Runnable {
    private static int RESET_TIME = 20;
    private final Minigame minigame;
    @Setter private int id;
    int ctr = RESET_TIME;

    public GameTimer(Minigame minigame) {
        this.minigame = minigame;
    }

    @Override
    public void run() {
        if (Bukkit.getOnlinePlayers().size() < minigame.getGameModule().getGameMeta().minimumPlayers()) {
            return;
        }
        if (ctr == RESET_TIME) {
            Bukkit.broadcastMessage(Language.GAME_INFO_ANNOUNCEMENT.getMsg());
        }
        ctr--;
        if (ctr <= 0) {
            ctr = RESET_TIME;
            Bukkit.getPluginManager().registerEvents(minigame.getGameListener(), Minigames.getMinigames().getPlugin());
            minigame.getGameModule().startGame();
            Bukkit.getScheduler().cancelTask(id);
            return;
        }
        if (ctr % 10 == 0 || ctr <= 3) {
            Bukkit.broadcastMessage(Language.TIMER_REMAINING.getMsg().replace("{REMAINING}",String.valueOf(ctr)));
        }
    }
    /*
        gameModule = minigame.getGameModule();
        Bukkit.getPluginManager().registerEvents(minigame.getGameListener(),plugin);
        gameModule.startGame();
     */
}
