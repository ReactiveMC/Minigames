package net.thelightmc.minigames.splegg;

import net.thelightmc.minigames.Minigame;

public class Splegg extends Minigame {
    public Splegg() {
        super(new SpleggGame(),new SpleggListener());
    }
}
