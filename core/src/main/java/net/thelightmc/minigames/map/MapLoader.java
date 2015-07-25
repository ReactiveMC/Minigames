package net.thelightmc.minigames.map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;

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
        File[] directories = new File(PATH + File.separator + gameName).listFiles(File::isDirectory);
        for (File map : directories) {
            File mapInfo = new File(map.getPath() + File.separator + "map.json");
            if (!mapInfo.exists()) {
                continue;
            }
            loadWorld(map);
            try {
                return gson.fromJson(FileUtils.readFileToString(mapInfo), new TypeToken<Map>(){}.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    private void loadWorld(File directory) {
        if (Bukkit.getWorld("GameWorld") != null) {
            Bukkit.unloadWorld(Bukkit.getWorld("GameWorld"),false);
        }
        try {
            FileUtils.deleteDirectory(new File(PATH + File.separator + "GameWorld"));
            FileUtils.copyDirectory(directory, new File(PATH + File.separator + "GameWorld"));
            new WorldCreator("GameWorld").createWorld();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPath(String path) {
        MapLoader.PATH = path;
    }
}
