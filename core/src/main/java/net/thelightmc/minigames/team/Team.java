package net.thelightmc.minigames.team;

import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.utils.GameLocation;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public final class Team {
    private final List<GamePlayer> playerList;
    private final String name;
    private final org.bukkit.scoreboard.Team team;
    private GameLocation spawn;

    public Team(ChatColor chatColor, Scoreboard scoreboard) {
        this.name = chatColor + chatColor.name().toLowerCase();
        playerList = new ArrayList<>();
        this.team = scoreboard.registerNewTeam(name);
        this.team.setAllowFriendlyFire(false);
        this.team.setPrefix(chatColor.toString());
    }
    public void addPlayer(GamePlayer gamePlayer) {
        playerList.add(gamePlayer);
        gamePlayer.setTeam(this);
        team.addPlayer(gamePlayer.getPlayer().get());
        gamePlayer.getPlayer().get().sendMessage(Language.ADDED_TO_TEAM.getMsg().replace("{TEAM_NAME}", getName()));
    }

    public void destroy() {
        for (GamePlayer player : playerList) {
            player.setTeam(null);
            team.removePlayer(player.getPlayer().get());
        }
        playerList.clear();
        team.unregister();
    }

    public List<GamePlayer> getPlayers() {
        return playerList;
    }

    public String getName() {
        return ChatColor.translateAlternateColorCodes('&',name);
    }

    public void setSpawn(GameLocation spawn) {
        this.spawn = spawn;
    }

    public GameLocation getSpawn() {
        return spawn;
    }

    public void removePlayer(GamePlayer gamePlayer) {
        playerList.remove(gamePlayer);
        team.removePlayer(gamePlayer.getPlayer().get());
    }
}
