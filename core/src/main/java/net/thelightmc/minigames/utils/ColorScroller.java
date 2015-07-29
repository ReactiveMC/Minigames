package net.thelightmc.minigames.utils;

import org.bukkit.ChatColor;

public final class ColorScroller {
    private final static ChatColor PRIMARY = ChatColor.WHITE;
    private final static ChatColor SECOONDARY = ChatColor.GOLD;
    private final String message;
    private boolean swap;
    private int pos=-1;

    public ColorScroller(String message) {
        this.message = message;
        swap = true;
    }
    public String next() {
        pos++;
        if (pos > message.length()) {
            pos=0;
            swap = !swap;
        }
        return (swap ? PRIMARY : SECOONDARY) + ChatColor.BOLD.toString() + message.substring(0,pos) + (swap ? SECOONDARY : PRIMARY)   + ChatColor.BOLD.toString() + message.substring(pos);
    }
}
