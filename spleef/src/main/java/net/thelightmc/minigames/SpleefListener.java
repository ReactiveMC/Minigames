package net.thelightmc.minigames;

import net.thelightmc.minigames.game.GameListener;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.BlockIterator;


public class SpleefListener extends GameListener {
    protected SpleefListener() {
    }
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
        return false;
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
        return DeathResult.HANDLED;
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

    @SuppressWarnings("all")
    @EventHandler
    public void onSnowballHit(ProjectileHitEvent event) {
        Projectile ent = event.getEntity();
        if (!(ent instanceof Snowball)) {
            return;
        }
        World world = ent.getWorld();
        BlockIterator bi = new BlockIterator(world, ent.getLocation().toVector(), ent.getVelocity().normalize(), 0, 4);
        Block b;
        while (bi.hasNext()) {
            b = bi.next();
            if (b.getType() == Material.AIR) {
                break;
            }
            b.getWorld().spawnFallingBlock(b.getLocation(),b.getType(),b.getData());
            b.setType(Material.AIR);
        }
    }
}