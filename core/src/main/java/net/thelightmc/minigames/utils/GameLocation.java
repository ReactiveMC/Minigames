package net.thelightmc.minigames.utils;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.Serializable;

@Data
public final class GameLocation implements Serializable {
    private transient Location location;
    private final int x;
    private final int y;
    private final int z;
    private final String worldName;

    public GameLocation(Location location) {
        this.location = location;
        worldName = location.getWorld().getName();
        x = location.getBlockX();
        y = location.getBlockY();
        z = location.getBlockZ();
    }
    public Location getLocation() {
        if (location == null) {
            location = new Location(Bukkit.getWorld(worldName),x,y,z);
        }
        return location;
    }
}
