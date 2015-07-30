package net.thelightmc.minigames.spleef;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.spectator.Spectator;
import net.thelightmc.minigames.game.GameListener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;


public class SpleefListener extends GameListener {
    @Override
    public boolean allowChat() {
        return true;
    }

    @Override
    public boolean openInventory(HumanEntity player, Inventory inventory) {
        return true;
    }

    @Override
    public boolean onInteract(Player player, Block clickedBlock) {
        return true;
    }

    @Override
    public boolean allowItemDrop() {
        return false;
    }

    @Override
    public boolean allowEntityDamage() {
        return true;
    }

    @Override
    public boolean allowBlockPlace() {
        return false;
    }

    @Override
    public boolean allowBlockBreak() {
        return true;
    }

    @Override
    public DeathResult onDeath(Player player) {
        PlayerRegistery.getPlayer(player).removeFromGame();
        return DeathResult.SPECTATOR;
    }

    @SuppressWarnings("all")
    @EventHandler
    public void onPlayerDamageBlock(BlockDamageEvent event) {
        if (Spectator.isSpectating(event.getPlayer())) {
            event.setCancelled(true);
            return;
        }
        event.setInstaBreak(true);
        Block block = event.getBlock();
        block.getWorld().spawnFallingBlock(block.getLocation(),
                block.getType(), block.getData());
    }

    @SuppressWarnings(value = "unused")
    @EventHandler
    public void onBlockLand(EntityChangeBlockEvent event) {
        if(event.getEntity() instanceof FallingBlock) {
            FallingBlock fallingBlock = (FallingBlock) event.getEntity();
            if(event.getBlock().getType() == Material.AIR){
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        fallingBlock.getLocation().getBlock().setType(Material.AIR);
                    }
                }.runTaskLater(Minigames.getMinigames().getPlugin(), 1L);
            }
        }
    }
}