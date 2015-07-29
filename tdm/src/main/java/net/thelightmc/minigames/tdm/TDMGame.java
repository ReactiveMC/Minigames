package net.thelightmc.minigames.tdm;

import net.thelightmc.minigames.game.GameMeta;
import net.thelightmc.minigames.team.Team;
import net.thelightmc.minigames.team.TeamGame;

@GameMeta(name = "TDM",description = "Team deathmatch \n" +
        "Kill the other team!")
public class TDMGame extends TeamGame {
    public TDMGame() {
        super(2);
    }

    @Override
    public Team checkEnd() {
        Team remaining = null;
        for (Team team : getTeams()) {
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
}
