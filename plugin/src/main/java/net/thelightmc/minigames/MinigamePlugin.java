package net.thelightmc.minigames;

import net.thelightmc.minigames.oitc.OITC;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.spleef.Spleef;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ThreadLocalRandom;

public class MinigamePlugin extends JavaPlugin {
    private Minigames minigames;
    @Override
    public void onEnable() {
        minigames = new Minigames(this);
        minigames.onEnable();
        //minigames.registerMinigame(new Spleef());
        minigames.registerMinigame(new OITC());

        getServer().getScheduler().runTaskLater(this, () -> Bukkit.getOnlinePlayers().forEach(p -> PlayerRegistery.registerPlayer(new GamePlayer(p.getUniqueId()))),5);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("start"))
            minigames.startMinigame(minigames.getMinigameList().get(ThreadLocalRandom.current().nextInt(minigames.getMinigameList().size())));
        return true;
    }
}
