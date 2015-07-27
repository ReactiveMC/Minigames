package net.thelightmc.minigames;

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
        getScoreboard().setTitle(GREEN + BOLD.toString() + getGameMeta().name());
        getScoreboard().addLabel(new BasicLabel(AQUA + BOLD.toString() + "Game",15));
        getScoreboard().addLabel(new BasicLabel("Spleef",14));
        getScoreboard().addLabel(new BlankLabel(13));
        getScoreboard().addLabel(new BasicLabel(YELLOW + BOLD.toString() + "Map", 12));
        getScoreboard().addLabel(new BasicLabel("Map loading...",11));
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
        new Spectator(gamePlayer);
        remainingPlayers--;
        checkEnd();
    }

    @Override
    public void load() {
        super.load();
        ScoreboardLabel label = getScoreboard().getLabel(11);
        getScoreboard().removeLabel(label);
        label.setText(getMap().getName());
        getScoreboard().addLabel(label);
    }
}
