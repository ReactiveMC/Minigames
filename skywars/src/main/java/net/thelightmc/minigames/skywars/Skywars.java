package net.thelightmc.minigames.skywars;

import net.thelightmc.minigames.Minigame;

public class Skywars extends Minigame {
    public Skywars() {
        super(new SkywarsGame(), new SkywarsListener());
    }
}
