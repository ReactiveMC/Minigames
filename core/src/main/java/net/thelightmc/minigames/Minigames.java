package net.thelightmc.minigames;

import lombok.Getter;
import net.thelightmc.minigames.commands.CmdGame;
import net.thelightmc.minigames.commands.sub.*;
import net.thelightmc.minigames.listeners.CoreListener;
import net.thelightmc.minigames.listeners.EditListener;
import net.thelightmc.minigames.map.MapLoader;
import net.thelightmc.minigames.timers.GameTimer;
import net.thelightmc.minigames.timers.ScoreboardTimer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Minigames {
    //Declaration
    @Getter private static Minigames minigames;
    private Minigame minigame;
    private final List<Minigame> minigameList = new ArrayList<>();
    private final JavaPlugin plugin;
    private boolean rotating = true;
    private GameTimer timer;

    public Minigames(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        minigames = this;
        EditListener editListener = new EditListener();
        plugin.getCommand("game").setExecutor(new CmdGame(new CmdEdit(),new CmdSavemap(editListener), new CmdCancel(editListener), new CmdEnd(), new CmdStart()));
        Bukkit.getPluginManager().registerEvents(new CoreListener(), plugin);
        Bukkit.getPluginManager().registerEvents(editListener,plugin); //TODO: Change this
        MapLoader.setPath(plugin.getDataFolder().getPath());
        Bukkit.getScheduler().runTaskTimer(plugin, new ScoreboardTimer(), 10, 10);
        startMinigame(getMinigameList().get(ThreadLocalRandom.current().nextInt(getMinigameList().size())));
    }
    void onDisable() {
        minigames = null;
        minigameList.forEach(Minigame::onDisable);
    }

    public Location getLobby() {
        return Bukkit.getWorlds().get(0).getSpawnLocation();
    }

    public void registerMinigame(Minigame minigame) {
        minigameList.add(minigame);
    }

    private void startMinigame(Minigame minigame) {
        setMinigame(minigame);
        timer = new GameTimer(minigame);
        timer.setId(Bukkit.getScheduler().runTaskTimer(plugin, timer, 20, 20).getTaskId());
    }

    public void setMinigame(Minigame minigame) {
        this.minigame = minigame;
        if (this.minigame == null && isRotating()) {
            //assume the game is ending and restart it.
            startMinigame(getMinigameList().get(ThreadLocalRandom.current().nextInt(getMinigameList().size())));
        }
    }
}
