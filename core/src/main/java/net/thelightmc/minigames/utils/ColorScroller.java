package net.thelightmc.minigames.utils;

import org.bukkit.ChatColor;

import java.util.Arrays;

public final class ColorScroller {
    private final static ChatColor PRIMARY_COLOR = ChatColor.YELLOW;
    private final static ChatColor SECONDARY_COLOR = ChatColor.GOLD;
    private final String message;
    private int pos=-1;

    public ColorScroller(String message) {
        this.message = message;
    }
    public String next() {
        pos++;
        if (pos > message.length()) {
            pos=0;
        }
        return ChatColor.translateAlternateColorCodes('&',insertIntoString(pos));
    }
    private String insertIntoString(int index) {
        char[] chars = message.toCharArray();
        chars = Arrays.copyOf(chars, chars.length + 1);
        System.arraycopy(chars, index, chars, index + 1, chars.length - index - 1);
        chars[index] = PRIMARY_COLOR.getChar();
        chars = Arrays.copyOf(chars, chars.length + 1);
        System.arraycopy(chars, index, chars, index + 1, chars.length - index - 1);
        chars[index] = '&';
        return new String(chars);
    }
}
