package net.thelightmc.minigames.oitc;

import net.thelightmc.minigames.game.GameListener;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.score.Scoring;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;

public class OITCListener extends GameListener {
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
        return false;
    }

    @Override
    public DeathResult onDeath(Player player) {
        Scoring scoring = (Scoring) PlayerRegistery.getPlayer(player).getGame();
        scoring.incrementScore(PlayerRegistery.getPlayer(player));
        return DeathResult.RESPAWN;
    }

    @SuppressWarnings(value = "unused")
    @EventHandler
    public void onArrowShot(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow) {
            if (event.getEntity() instanceof Player) {
                ((Player)event.getEntity()).setHealth(0);
            }
        }
    }
}
