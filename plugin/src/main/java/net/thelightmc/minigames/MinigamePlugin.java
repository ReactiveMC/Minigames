package net.thelightmc.minigames;

import org.bukkit.plugin.java.JavaPlugin;

public class MinigamePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Minigames minigames = new Minigames(this);
        minigames.onEnable();
        minigames.registerMinigame(new Spleef());
    }

    @Override
    public void onDisable() {

    }
}
