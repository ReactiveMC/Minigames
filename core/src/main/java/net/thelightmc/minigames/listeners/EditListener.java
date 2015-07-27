package net.thelightmc.minigames.listeners;

import lombok.Getter;
import lombok.Setter;
import net.thelightmc.minigames.player.PlayerRegistery;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class EditListener implements Listener {
    private static final Material SPAWN_BLOCK = Material.BEACON;
    private static final Material MINIMUM_BLOCK = Material.ENCHANTMENT_TABLE;
    @Getter private final List<Location> spawns = new ArrayList<>();
    @Getter @Setter private int minimumLocation;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().isSneaking()) {
            return;
        }
        if (PlayerRegistery.getPlayer(event.getPlayer()).isEditing()) {
            Block blockPlaced = event.getBlockPlaced();
            if (blockPlaced.getType() == SPAWN_BLOCK) {
                event.getPlayer().sendMessage(ChatColor.GREEN + "Added a spawn point.");
                event.setCancelled(true);
                spawns.add(blockPlaced.getLocation());
            }
            if (blockPlaced.getType() == MINIMUM_BLOCK) {
                int y = blockPlaced.getLocation().getBlockY();
                event.getPlayer().sendMessage(ChatColor.GREEN + "Set the minimum point to Y: " + y);
                event.setCancelled(true);
                minimumLocation = y;
            }
        }
    }
}
