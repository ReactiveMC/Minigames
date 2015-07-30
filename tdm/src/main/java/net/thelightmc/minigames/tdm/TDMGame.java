package net.thelightmc.minigames.tdm;

import net.md_5.bungee.api.ChatColor;
import net.thelightmc.minigames.game.GameMeta;
import net.thelightmc.minigames.team.GameTeam;
import net.thelightmc.minigames.team.TeamBuilder;
import net.thelightmc.minigames.team.TeamGame;

@GameMeta(name = "TDM",description = "Team deathmatch \n" +
        "Kill the other team!")
public class TDMGame extends TeamGame {
    public TDMGame() {
        super();
    }

    @Override
    public GameTeam checkEnd() {
        GameTeam remaining = null;
        for (GameTeam team : getTeams()) {
            if (team.getSize() > 0) {
                if (remaining == null) {
                    remaining = team;
                } else {
                    return null;
                }
            }
        }
        return remaining;
    }

    @Override
    public void load() {
        super.load();
        this.getTeams().add(new TeamBuilder(ChatColor.BLUE + "Blue").prefix(ChatColor.BLUE.toString()).scorebaord(
                getScoreboard().getScoreboard()).spawn(getMap().getSpawnPoints().get(0)).build());
        this.getTeams().add(new TeamBuilder(ChatColor.RED + "Red").prefix(ChatColor.RED.toString()).scorebaord(
                getScoreboard().getScoreboard()).spawn(getMap().getSpawnPoints().get(1)).build());
    }
}
