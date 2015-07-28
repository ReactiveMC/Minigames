package net.thelightmc.minigames.game;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GameMeta {
    int minimumPlayers() default 2;
    int maximumPlayers() default -1;
    String name();
    String description();
}
