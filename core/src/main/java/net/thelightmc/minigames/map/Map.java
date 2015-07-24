package net.thelightmc.minigames.map;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import org.bukkit.Location;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("all")
@Data
public class Map {
    private final String name;
    private final ImmutableList<Location> spawnPoints;
    private final String gameType;
    public Location getSpawn() {
        return spawnPoints.get(ThreadLocalRandom.current().nextInt(spawnPoints.size()));
    }
}
