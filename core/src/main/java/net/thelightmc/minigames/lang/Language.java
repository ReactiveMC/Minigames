package net.thelightmc.minigames.lang;

import net.thelightmc.minigames.player.GamePlayer;
import net.thelightmc.minigames.utils.TagReplacer;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public enum Language {
    GAME_STARTING("&f&lThe game is now starting. Game on!!"),
    GAME_INFO_ANNOUNCEMENT("&f&lNow starting &6&l{GAME_NAME} &f&lon &6&l{MAP_NAME}"),
    SCOREBOARD_TITLE("&f&lMinigames"),
    TIMER_REMAINING("&f&lThe next game is starting in &6&l{REMAINING} &f&lseconds."),
    ADDED_TO_TEAM("&eYou have been added to the {TEAM_NAME} team."),
    TEAM_WON("&c&lWinner - {TEAM_NAME}");
    public enum Tag {
        GAME_NAME,MAP_NAME;
        private String getReplacement() {
            return "{" + name() + "}";
        }
    }
    private String msg;
    Language(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return ChatColor.translateAlternateColorCodes('&',msg);
    }
    public String translate(GamePlayer gamePlayer) {
        String s = msg;
        for (Tag tag : Tag.values()) {
            if (this.getMsg().contains(tag.getReplacement())) {
                s = s.replace(tag.getReplacement(), TagReplacer.getReplacement(tag, gamePlayer));
            }
        }
        return s;
    }
    public void load(File file) {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        for (Language language : values()) {
            language.msg = configuration.getString(language.name());
        }
    }
}
