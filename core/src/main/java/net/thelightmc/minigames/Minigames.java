package net.thelightmc.minigames;

import lombok.Getter;
import net.thelightmc.minigames.game.GameModule;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Minigames extends JavaPlugin {
    @Getter private static Minigames minigames;
    private GameModule gameModule;

    @Override
    public void onEnable() {
        minigames = this;
    }

    public Location getLobby() {
        return new Location(Bukkit.getWorlds().get(0), 0, 100, 0);
    }

    public GameModule getGameModule() {
        return gameModule;
    }

    public void setGameModule(GameModule gameModule) {
        this.gameModule = gameModule;
    }
}
