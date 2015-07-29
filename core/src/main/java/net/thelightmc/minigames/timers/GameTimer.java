package net.thelightmc.minigames.timers;

import lombok.Setter;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.Minigame;
import net.thelightmc.minigames.game.GameListener;
import net.thelightmc.minigames.game.GameModule;
import org.bukkit.Bukkit;

public class GameTimer implements Runnable {
    private static int RESET_TIME = 20;
    private final Minigame minigame;
    @Setter private int id;
    int ctr = RESET_TIME;

    public GameTimer(Minigame minigame) {
        this.minigame = minigame;
        minigame.getGameModule().load();
    }

    @Override
    public void run() {
        GameModule gameModule = minigame.getGameModule();
        if (Bukkit.getOnlinePlayers().size() < gameModule.getGameMeta().minimumPlayers()) {
            return;
        }
        if (ctr == RESET_TIME) {
            Bukkit.broadcastMessage(Language.GAME_INFO_ANNOUNCEMENT.getMsg().replace("{GAME_NAME}",gameModule.getGameMeta().name()).replace("{MAP_NAME}",gameModule.getMap().getName()));
            gameModule.sendScoreboard();
        }
        ctr--;
        if (ctr <= 0) {
            ctr = RESET_TIME;
            GameListener listener = minigame.getGameListener();
            Bukkit.getPluginManager().registerEvents(listener, Minigames.getMinigames().getPlugin());
            listener.setMap(gameModule.getMap());
            gameModule.startGame();
            Bukkit.getScheduler().cancelTask(id);
            return;
        }
        if (ctr % 10 == 0 || ctr <= 3) {
            Bukkit.broadcastMessage(Language.TIMER_REMAINING.getMsg().replace("{REMAINING}",String.valueOf(ctr)));
        }
    }

    public void setTime(int time) {
        this.ctr = time;
    }
    /*
        gameModule = minigame.getGameModule();
        Bukkit.getPluginManager().registerEvents(minigame.getGameListener(),plugin);
        gameModule.startGame();
     */
}
