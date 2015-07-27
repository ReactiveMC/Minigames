package net.thelightmc.minigames.spleef;

import net.thelightmc.minigames.Minigame;

public class Spleef extends Minigame {
    public Spleef() {
        super(new SpleefGame(),new SpleefListener());
    }
}
