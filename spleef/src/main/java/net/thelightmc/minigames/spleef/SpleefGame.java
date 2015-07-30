package net.thelightmc.minigames.spleef;

import net.thelightmc.minigames.game.GameMeta;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.scoreboard.ScoreboardLabel;
import net.thelightmc.minigames.scoreboard.label.BasicLabel;
import net.thelightmc.minigames.scoreboard.label.BlankLabel;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.ChatColor.*;

@GameMeta(name = "Spleef",description = "Players have to break blocks in order to cause" +
        " the others to fall.")
public class SpleefGame extends GameModule {
    public SpleefGame() {
        super();
    }
    private int remainingPlayers;
    @Override
    public void startGame() {
        super.startGame();
        Bukkit.getOnlinePlayers().forEach(p -> p.getInventory().addItem(
                new ItemStack(Material.SNOW_BALL,6)));
        remainingPlayers = Bukkit.getOnlinePlayers().size();
    }

    private void checkEnd() {
        if (remainingPlayers < getGameMeta().minimumPlayers()) {
            endGame();
        }
    }

    @Override
    public void removePlayer(GamePlayer gamePlayer) {
        remainingPlayers--;
        checkEnd();
    }
}
