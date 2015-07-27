package net.thelightmc.minigames;

import lombok.Getter;
import lombok.Setter;
import net.thelightmc.minigames.commands.CmdGame;
import net.thelightmc.minigames.commands.sub.CmdCancel;
import net.thelightmc.minigames.commands.sub.CmdEdit;
import net.thelightmc.minigames.commands.sub.CmdSavemap;
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

public class Minigames {
    @Getter private static Minigames minigames;
    @Getter @Setter private Minigame minigame;
    @Getter @SuppressWarnings("all")private final List<Minigame> minigameList = new ArrayList<>();
    @Getter private final JavaPlugin plugin;

    public Minigames(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        minigames = this;
        EditListener editListener = new EditListener();
        plugin.getCommand("game").setExecutor(new CmdGame(new CmdEdit(),new CmdSavemap(editListener), new CmdCancel(editListener)));
        Bukkit.getPluginManager().registerEvents(new CoreListener(),plugin);
        Bukkit.getPluginManager().registerEvents(editListener,plugin); //TODO: Change this
        MapLoader.setPath(plugin.getDataFolder().getPath());
        Bukkit.getScheduler().runTaskTimer(plugin, new ScoreboardTimer(),20,20);
    }

    public Location getLobby() {
        return Bukkit.getWorlds().get(0).getSpawnLocation();
    }

    public void registerMinigame(Minigame minigame) {
        minigameList.add(minigame);
    }

    public void startMinigame(Minigame minigame) {
        setMinigame(minigame);
        GameTimer timer = new GameTimer(minigame);
        timer.setId(Bukkit.getScheduler().runTaskTimer(plugin, timer,20,20).getTaskId());
    }
}
