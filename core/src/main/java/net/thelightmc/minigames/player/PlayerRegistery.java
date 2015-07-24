package net.thelightmc.minigames.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerRegistery {
    private final static Map<String,GamePlayer> REGISTERY = new HashMap<>();
    public static GamePlayer getPlayer(String name) {
        return REGISTERY.get(name);
    }
    public static GamePlayer getPlayer(Player player) {
        return getPlayer(player.getName());
    }
    public static GamePlayer getPlayer(UUID uuid) {
        return getPlayer(Bukkit.getPlayer(uuid));
    }
    public static void registerPlayer(GamePlayer gamePlayer) {
        REGISTERY.remove(gamePlayer.getPlayer().get().getName());
    }
    public static void unregisterPlayer(GamePlayer gamePlayer) {
        REGISTERY.remove(gamePlayer.getPlayer().get().getName());
    }
}
