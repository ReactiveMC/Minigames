package net.thelightmc.minigames.runner;

import net.thelightmc.minigames.game.GameMeta;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.player.GamePlayer;
import org.bukkit.Bukkit;

@GameMeta(name = "Runner",description = "Top kek minigame")
public class RunnerGame extends GameModule {
    private int remainingPlayers;
    public RunnerGame() {
        super();
    }

    @Override
    public void removePlayer(GamePlayer gamePlayer) {
        remainingPlayers --;
    }

    @Override
    public void startGame() {
        super.startGame();
        remainingPlayers = Bukkit.getOnlinePlayers().size();
        if (checkEnd())
            endGame();
    }

    private boolean checkEnd() {
        return this.remainingPlayers - 1 <= Bukkit.getOnlinePlayers().size();
    }
}