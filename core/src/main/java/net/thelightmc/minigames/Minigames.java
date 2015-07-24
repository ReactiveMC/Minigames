package net.thelightmc.minigames;

import lombok.Getter;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.timers.GameTimer;
import net.thelightmc.minigames.timers.ScoreboardTimer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Minigames {
    @Getter private static Minigames minigames;
    @Getter private GameModule gameModule;
    private final List<Minigame> minigameList = new ArrayList<>();
    private final Plugin plugin;

    public Minigames(Plugin plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        minigames = this;
        Bukkit.getScheduler().runTaskTimer(plugin, new GameTimer(),20,20);
        Bukkit.getScheduler().runTaskTimer(plugin, new ScoreboardTimer(),20,20);
    }

    public Location getLobby() {
        return new Location(Bukkit.getWorlds().get(0), 0, 100, 0);
    }

    public void registerMinigame(Minigame minigame) {
        minigameList.add(minigame);
    }

    public List<Minigame> getMinigameList() {
        return minigameList;
    }

    public void startMinigame(Minigame minigame) {
        gameModule = minigame.getGameModule();
        Bukkit.getPluginManager().registerEvents(minigame.getGameListener(),plugin);
        gameModule.startGame();
    }
}
