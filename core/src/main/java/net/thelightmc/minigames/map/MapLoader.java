package net.thelightmc.minigames.map;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.thelightmc.minigames.utils.GameLocation;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class MapLoader {
    private static String PATH;
    private static MapLoader mapLoader;
    private final Gson gson;

    public MapLoader() {
        this.gson = new Gson();
    }

    public static MapLoader get() {
        if (mapLoader == null) {
            mapLoader = new MapLoader();
        }
        return mapLoader;
    }

    public Map loadRandomMap(String gameName) {
        File[] directories = new File(PATH + File.separator + gameName.toLowerCase()).listFiles(File::isDirectory);
        for (File map : directories) {
            File mapInfo = new File(map.getPath() + File.separator + "map.json");
            if (!mapInfo.exists()) {
                continue;
            }
            loadWorld(map,"GameWorld");
            try {
                return gson.fromJson(FileUtils.readFileToString(mapInfo), new TypeToken<Map>(){}.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public World loadWorld(File directory,String worldName) {
        if (Bukkit.getWorld(worldName) != null) {
            Bukkit.unloadWorld(Bukkit.getWorld(worldName),false);
        }
        try {
            FileUtils.deleteDirectory(new File(PATH + File.separator + worldName));
            FileUtils.copyDirectory(directory, new File(worldName));
            return new WorldCreator(worldName).createWorld();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setPath(String path) {
        MapLoader.PATH = path;
    }

    public boolean isMap(String gameName,String mapName) {
        return new File(PATH + File.separator + gameName.toLowerCase() + File.separator + mapName.toLowerCase()).isDirectory();
    }
    public World loadEditMap(String gameName,String mapName) {
        return loadWorld(new File(PATH + File.separator + gameName.toLowerCase() + File.separator + mapName.toLowerCase()), "Edit");
    }
    public boolean createMap(String gameName,String mapName) {
        File file = new File(PATH + File.separator + "template");
        if (!file.isDirectory()) {
            return false;
        }
        try {
            FileUtils.copyDirectory(file, new File(PATH + File.separator + gameName.toLowerCase() + File.separator + mapName.toLowerCase()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveMap(String game, String mapName, List<Location> spawns, World world, String builder, int minimumLocation) {
        for (Player player : world.getPlayers()) {
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        }
        File folder = world.getWorldFolder();
        Bukkit.unloadWorld(world,true);
        try {
            File target = new File(PATH + File.separator + game.toLowerCase() + File.separator + mapName.toLowerCase());
            FileUtils.copyDirectory(folder, target);
            Map map = new Map(mapName,builder, ImmutableList.copyOf(spawns.stream().map(GameLocation::new).collect(Collectors.toList())),minimumLocation);
            PrintWriter printWriter = new PrintWriter(new File(target + File.separator + "map.json"));
            printWriter.write(gson.toJson(map));
            printWriter.flush();
            printWriter.close();
            new File(target + File.separator + "uid.dat").delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
