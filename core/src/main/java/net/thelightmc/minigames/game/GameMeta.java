package net.thelightmc.minigames.game;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GameMeta {
    int minimumPlayer() default 2;
}
