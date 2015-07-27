package net.thelightmc.minigames.map;

import lombok.Getter;
import lombok.Setter;
import net.thelightmc.minigames.utils.GameLocation;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Map implements Serializable {
    private final String name;
    private final String builder;
    private final List<GameLocation> spawnPoints;
    private final int minumumY;
    public Location getSpawn() {
        return spawnPoints.get(ThreadLocalRandom.current().nextInt(spawnPoints.size())).getLocation();
    }
    public Map(String name,String builder, List<GameLocation> spawns) {
        this(name, builder, spawns,0);
    }
    public Map(String name,String builder, List<GameLocation> spawns,int minumumY) {
        this.name = name;
        this.builder = builder;
        this.spawnPoints = spawns;
        this.minumumY = minumumY;
    }
}
