package net.thelightmc.minigames.skywars;

import net.thelightmc.minigames.game.GameListener;
import net.thelightmc.minigames.player.PlayerRegistery;
import net.thelightmc.minigames.spectator.Spectator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SkywarsListener extends GameListener {
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
        return true;
    }

    @Override
    public boolean allowEntityDamage() {
        return true;
    }

    @Override
    public boolean allowBlockPlace() {
        return true;
    }

    @Override
    public boolean allowBlockBreak() {
        return true;
    }

    @Override
    public DeathResult onDeath(Player player) {
        new Spectator(PlayerRegistery.getPlayer(player));
        return DeathResult.HANDLED;
    }
}
