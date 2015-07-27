package net.thelightmc.minigames.oitc;

import net.thelightmc.minigames.Minigame;

public class OITC extends Minigame {
    public OITC() {
        super(new OITCGame(),new OITCListener());
    }
}
