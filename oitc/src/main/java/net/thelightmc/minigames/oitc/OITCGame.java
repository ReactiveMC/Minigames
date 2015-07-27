package net.thelightmc.minigames.oitc;

import net.thelightmc.minigames.game.GameMeta;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.score.ScoreComparator;
import net.thelightmc.minigames.score.Scoring;
import net.thelightmc.minigames.scoreboard.ScoreboardLabel;
import net.thelightmc.minigames.scoreboard.label.BasicLabel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@GameMeta(name = "OITC",description = "Pwn people with bows and arrows")
public class OITCGame extends GameModule implements Scoring {
    private static final int SCORE_CAP = 15;
    private static final int INCREMENT = 1;
    private final Map<String,Integer> scores;
    public OITCGame() {
        super();
        scores = new HashMap<>();
    }
    @Override
    public void startGame() {
        super.startGame();
        Bukkit.getOnlinePlayers().forEach(this::equip);
        //sendScoreboard();
    }
    protected void equip(Player p) {
        p.getInventory().setItem(0, new ItemStack(Material.BOW));
        p.getInventory().setItem(1, new ItemStack(Material.ARROW));
        p.getInventory().setItem(2, new ItemStack(Material.STONE_SWORD));
    }

    @Override
    public int getScore(GamePlayer gamePlayer) {
        return scores.get(gamePlayer.getName());
    }

    @Override
    public void incrementScore(GamePlayer gamePlayer) {
        String name = gamePlayer.getName();
        if (!scores.containsKey(name)) {
            scores.put(name,INCREMENT);
        }
        scores.put(name,scores.get(name)+INCREMENT);
        updateScoreboard(gamePlayer);
        if (getScore(gamePlayer) >= getScoreCap()) {
            endGame();
        }
    }

    private void updateScoreboard(GamePlayer player) {
        String name = player.getName();
        ScoreboardLabel label = getScoreboard().getLabel(name);
        if (label != null) {
            getScoreboard().removeLabel(label);
            label.setPosition(getScore(player));
        } else {
            label = new BasicLabel(name,getScore(player));
        }
        getScoreboard().addLabel(label);
    }

    @Override
    public void setScore(GamePlayer gamePlayer, int score) {
        scores.put(gamePlayer.getName(),score);
        updateScoreboard(gamePlayer);
    }

    @Override
    public Map<String, Integer> sortScores() {
        return new TreeMap<>(new ScoreComparator(scores));
    }

    @Override
    public int getScoreCap() {
        return SCORE_CAP;
    }
}
