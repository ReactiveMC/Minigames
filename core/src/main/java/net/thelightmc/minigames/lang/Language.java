package net.thelightmc.minigames.lang;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public enum Language {
    GAME_STARTING(""),
    SCOREBOARD_TITLE("&");
    private String msg;
    Language(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    public void load(File file) {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        for (Language language : values()) {
            language.msg = configuration.getString(language.name());
        }
    }
}
