package net.thelightmc.minigames.map;

import lombok.Data;
import net.thelightmc.minigames.utils.GameLocation;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("all")
@Data
public class Map implements Serializable {
    private final String name;
    private final String builder;
    private final List<GameLocation> spawnPoints;
    public Location getSpawn() {
        return spawnPoints.get(ThreadLocalRandom.current().nextInt(spawnPoints.size())).getLocation();
    }
}
