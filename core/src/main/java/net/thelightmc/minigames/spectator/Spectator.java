package net.thelightmc.minigames.spectator;

import net.thelightmc.minigames.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Spectator {
    public Spectator(GamePlayer gamePlayer) {
        PLAYERS.add(gamePlayer.getUniqueID());
        Player bukkitPlayer = gamePlayer.getPlayer().get();
        bukkitPlayer.setAllowFlight(true);
        bukkitPlayer.teleport(gamePlayer.getGame().getMap().getRandomSpawn());
        Bukkit.getOnlinePlayers().forEach(p -> hide(bukkitPlayer,p));
    }

    private void hide(Player bukkitPlayer, Player p) {
        p.hidePlayer(bukkitPlayer);
    }

    private final static Set<UUID> PLAYERS = new HashSet<>();

    public static Set<UUID> getPlayers() {
        return PLAYERS;
    }

    public static boolean isSpectating(Player player) {
        return PLAYERS.contains(player.getUniqueId());
    }
    public static boolean isSpectating(GamePlayer gamePlayer) {
        return PLAYERS.contains(gamePlayer.getUniqueID());
    }
}
