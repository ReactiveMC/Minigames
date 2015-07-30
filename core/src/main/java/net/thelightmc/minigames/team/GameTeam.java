package net.thelightmc.minigames.team;

import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.utils.GameLocation;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashSet;
import java.util.Set;

public final class GameTeam {
    private final String name;
    private final String prefix;
    private final Scoreboard scoreboard;
    private final GameLocation spawn;
    private final Team team;
    private final Set<GamePlayer> gamePlayers;
    private final int max;

    public GameTeam(String name, String prefix, Scoreboard scoreboard, GameLocation spawn, boolean friendlyFire, int max) {
        this.name = name;
        this.prefix = prefix;
        this.scoreboard = scoreboard;
        this.spawn = spawn;
        this.max = max;
        this.team = scoreboard.registerNewTeam(name);
        this.team.setPrefix(prefix);
        this.team.setAllowFriendlyFire(friendlyFire);
        this.team.setDisplayName(name);
        gamePlayers = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public GameLocation getSpawn() {
        return spawn;
    }

    public Team getTeam() {
        return team;
    }
    public void destroy() {
        gamePlayers.forEach(g -> g.setTeam(null));
        team.unregister();
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void addPlayer(GamePlayer gamePlayer) {
        gamePlayers.add(gamePlayer);
        gamePlayer.setTeam(this);
        team.addEntry(gamePlayer.getName());
        gamePlayer.getPlayer().get().sendMessage(Language.ADDED_TO_TEAM.getMsg().replace("{TEAM_NAME}", getName()));
    }

    public int getSize() {
        return getGamePlayers().size();
    }

    public void removePlayer(GamePlayer gamePlayer) {
        team.removeEntry(gamePlayer.getName());
    }

    public int getMax() {
        return max;
    }
    /*
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

    public int getSize() {
        return getPlayers().size();
    }
    */
}
