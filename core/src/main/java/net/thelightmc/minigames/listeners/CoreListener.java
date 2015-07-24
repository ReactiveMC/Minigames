package net.thelightmc.minigames.listeners;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.player.PlayerRegistery;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CoreListener implements Listener {
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        GamePlayer gamePlayer = new GamePlayer(event.getPlayer().getUniqueId());
        PlayerRegistery.registerPlayer(gamePlayer);
        event.getPlayer().teleport(Minigames.getMinigames().getLobby());
    }
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        PlayerRegistery.unregisterPlayer(PlayerRegistery.getPlayer(event.getPlayer()));
    }
}
