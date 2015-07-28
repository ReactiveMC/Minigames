package net.thelightmc.minigames.listeners;

import net.thelightmc.minigames.Minigame;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.utils.RespawnUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
    @EventHandler (priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }
    @EventHandler (priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockPlaceEvent event) {
        event.setCancelled(true);
    }
    @EventHandler (priority = EventPriority.LOWEST)
    public void onBlockBreak(EntitySpawnEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerLevel(FoodLevelChangeEvent event) {
        Minigame minigame = Minigames.getMinigames().getMinigame();
        if (minigame == null || !minigame.getGameModule().isRunning()) {
            event.setCancelled(true);
            event.setFoodLevel(20);
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        try {
            RespawnUtil.autoRespawnPlayer(event.getEntity());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Minigame minigame = Minigames.getMinigames().getMinigame();
        if (minigame == null || !minigame.getGameModule().isRunning()) {
            event.setCancelled(true);
        }
    }
}
