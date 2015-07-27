package net.thelightmc.minigames.game;

import lombok.Setter;
import net.thelightmc.minigames.map.Map;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;

@SuppressWarnings(value = "unused")
public abstract class GameListener implements Listener {
    @Setter private Map map;
    public abstract boolean allowChat();
    public abstract boolean openInventory(HumanEntity player,Inventory inventory);
    public abstract boolean onInteract(Player player, Block clickedBlock);
    public abstract boolean allowItemDrop();
    public abstract boolean allowEntityDamage();
    public abstract boolean allowBlockPlace();
    public abstract boolean allowBlockBreak();
    public abstract DeathResult onDeath(Player player);

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!allowChat()) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (!openInventory(event.getPlayer(), event.getInventory())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!onInteract(event.getPlayer(), event.getClickedBlock())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (!allowItemDrop()) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!allowEntityDamage()) {
            event.setCancelled(true);
        }
    }
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(!allowBlockPlace());
    }
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(!allowBlockBreak());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage("");
        switch (onDeath(event.getEntity())) {
            case HANDLED:
                return;
            case RESPAWN:
                break;
        }
    }
    @EventHandler
    public void onPlayerFall(PlayerMoveEvent event) {
        if (event.getTo().getBlockY() == event.getFrom().getY()) {
            return;
        }
        if (event.getTo().getY() < map.getMinumumY()) {
            if (Spectator.isSpectating(event.getPlayer())) {
                return;
            }
            event.getPlayer().setHealth(0);
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        PlayerRegistery.getPlayer(event.getPlayer()).removeFromGame();
    }

    public void disable() {
        HandlerList.unregisterAll(this);
    }

    public enum DeathResult {
        HANDLED,RESPAWN
    }
}
