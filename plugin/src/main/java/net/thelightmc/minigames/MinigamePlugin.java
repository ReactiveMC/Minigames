package net.thelightmc.minigames;

import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.spleef.Spleef;
import net.thelightmc.minigames.splegg.Splegg;
import net.thelightmc.minigames.tdm.TDM;
import net.thelightmc.minigames.runner.Runner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MinigamePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Minigames minigames = new Minigames(this);
        minigames.registerMinigame(new Spleef());
        minigames.registerMinigame(new TDM());
        minigames.registerMinigame(new Splegg());
        minigames.registerMinigame(new Runner());
        minigames.onEnable();
        getServer().getScheduler().runTaskLater(this, () -> Bukkit.getOnlinePlayers().forEach(p -> PlayerRegistery.registerPlayer(new GamePlayer(p.getUniqueId()))),5);
    }
}
