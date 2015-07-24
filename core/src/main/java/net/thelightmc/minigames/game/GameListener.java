package net.thelightmc.minigames.game;

import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

@SuppressWarnings(value = "unused")
public abstract class GameListener implements Listener {
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
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!allowBlockPlace()) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!allowBlockBreak()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage("");
        switch (onDeath(event.getEntity())) {
            case HANDLED:
                return;
            case RESPAWN:
                event.getEntity().spigot().respawn();
                break;
        }
    }

    public enum DeathResult {
        HANDLED,RESPAWN
    }
}
