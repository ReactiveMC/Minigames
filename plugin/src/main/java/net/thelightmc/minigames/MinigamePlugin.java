package net.thelightmc.minigames;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import net.thelightmc.minigames.map.Map;
import net.thelightmc.minigames.utils.GameLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class MinigamePlugin extends JavaPlugin {
    private Minigames minigames;
    @Override
    public void onEnable() {
        minigames = new Minigames(this);
        minigames.onEnable();
        minigames.registerMinigame(new Spleef());
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("start"))
            minigames.startMinigame(minigames.getMinigameList().get(ThreadLocalRandom.current().nextInt(minigames.getMinigameList().size())));
        if (command.getName().equalsIgnoreCase("save")) {
            Map map = new Map("Sky","TheLightMC", ImmutableList.of(new GameLocation(new Location(Bukkit.getWorld("world"),0,100,0))));
            try {
                PrintWriter printWriter = new PrintWriter(new File(getDataFolder() + File.separator + "map.json"));
                printWriter.write(new Gson().toJson(map));
                printWriter.flush();
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
