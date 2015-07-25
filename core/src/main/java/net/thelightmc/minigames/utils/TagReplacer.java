package net.thelightmc.minigames.utils;

import net.thelightmc.minigames.lang.Language;
import net.thelightmc.minigames.player.GamePlayer;

public class TagReplacer {
    public static String getReplacement(Language.Tag tag,GamePlayer gamePlayer) {
        switch (tag) {
            case GAME_NAME:
                return gamePlayer.getGame().getGameMeta().name();
            case MAP_NAME:
                return gamePlayer.getGame().getMap().getName();
        }
        return "";
    }
}
