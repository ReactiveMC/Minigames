package net.thelightmc.minigames.game;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GameMeta {
    int minimumPlayers() default 2;
    String description();
}
