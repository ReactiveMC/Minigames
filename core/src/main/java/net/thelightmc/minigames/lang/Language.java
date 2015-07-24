package net.thelightmc.minigames.lang;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public enum Language {
    GAME_STARTING("&f&lThe game is now starting. Game on!!"),
    SCOREBOARD_TITLE("&f&lMinigames"),
    TIMER_REMAINING("&f&lThe next game is starting in &6&l{REMAINING} &f&lseconds.");
    private String msg;
    Language(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return ChatColor.translateAlternateColorCodes('&',msg);
    }
    public void load(File file) {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        for (Language language : values()) {
            language.msg = configuration.getString(language.name());
        }
    }
}
