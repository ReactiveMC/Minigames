package net.thelightmc.minigames.splegg;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.game.GameListener;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class SpleggListener extends GameListener {
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
        if (player.getItemInHand().getType() == Material.DIAMOND_HOE) {
            Snowball snowball = player.getWorld().spawn(player.getLocation(), Snowball.class);
            snowball.setShooter(player);
            snowball.setVelocity(player.getLocation().getDirection().multiply(1.5));
        }
        return true;
    }

    @Override
    public boolean allowItemDrop() {
        return false;
    }

    @Override
    public boolean allowEntityDamage() {
        return false;
    }

    @Override
    public boolean allowBlockPlace() {
        return false;
    }

    @Override
    public boolean allowBlockBreak() {
        return false;
    }

    @Override
    public DeathResult onDeath(Player player) {
        PlayerRegistery.getPlayer(player).removeFromGame();
        return DeathResult.SPECTATOR;
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

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Snowball) {
            Location loc = entity.getLocation();
            Vector vec = entity.getVelocity();
            Location loc2 = new Location(loc.getWorld(), loc.getX() + vec.getX(), loc.getY() + vec.getY(), loc.getZ() + vec.getZ());
            Block block = loc2.getBlock();
            loc2.getWorld().spawnFallingBlock(loc2, block.getType(), block.getData());
            block.setType(Material.AIR);
        }
    }
}