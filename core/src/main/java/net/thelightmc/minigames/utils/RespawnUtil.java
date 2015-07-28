package net.thelightmc.minigames.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RespawnUtil {
    @SuppressWarnings(value = "all")
    public static void autoRespawnPlayer(Player who) throws ReflectiveOperationException {
        String bukkitversion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
        Class<?> cp = Class.forName("org.bukkit.craftbukkit." + bukkitversion + ".entity.CraftPlayer");
        Class<?> clientcmd = Class.forName("net.minecraft.server." + bukkitversion + ".PacketPlayInClientCommand");
        Class enumClientCMD = Class.forName("net.minecraft.server." + bukkitversion + ".EnumClientCommand");
        Method handle = cp.getDeclaredMethod("getHandle", null);
        Object entityPlayer = handle.invoke(who, null);
        Constructor<?> packetconstr = clientcmd.getDeclaredConstructor(enumClientCMD);
        Enum<?> num = Enum.valueOf(enumClientCMD, "PERFORM_RESPAWN");
        Object packet = packetconstr.newInstance(num);
        Object playerconnection = entityPlayer.getClass().getDeclaredField("playerConnection").get(entityPlayer);
        Method send = playerconnection.getClass().getDeclaredMethod("a", clientcmd);
        send.invoke(playerconnection, packet);
    }
}
