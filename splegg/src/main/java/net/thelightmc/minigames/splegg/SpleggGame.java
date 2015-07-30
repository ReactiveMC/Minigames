package net.thelightmc.minigames.splegg;

import net.thelightmc.minigames.game.GameMeta;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@GameMeta(name = "Splegg",description = "Players have to break blocks in order to cause" +
        " the others to fall.")
public class SpleggGame extends GameModule {
    public SpleggGame() {
        super();
    }
    private int remainingPlayers;
    @Override
    public void startGame() {
        super.startGame();
        Bukkit.getOnlinePlayers().forEach(p -> p.getInventory().addItem(new ItemStack(Material.DIAMOND_HOE)));
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

    @Override
    public void load() {
        super.load();
    }
}
