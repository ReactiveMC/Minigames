package net.thelightmc.minigames;

import net.thelightmc.minigames.game.GameMeta;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@GameMeta
public class SpleefGame extends GameModule {
    private int remainingPlayers;
    @Override
    public void startGame() {
        super.startGame();
        Bukkit.getOnlinePlayers().forEach(p -> p.getInventory().addItem(new ItemStack(Material.SNOW_BALL,6)));
        remainingPlayers = Bukkit.getOnlinePlayers().size();
    }

    protected void checkEnd() {
        if (remainingPlayers < getGameMeta().minimumPlayer()) {
            endGame();
        }
    }

    @Override
    public void removePlayer(GamePlayer gamePlayer) {
        super.removePlayer(gamePlayer);
        remainingPlayers--;
    }
}
