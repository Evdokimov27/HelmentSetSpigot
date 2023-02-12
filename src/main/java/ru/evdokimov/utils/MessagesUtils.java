package ru.evdokimov.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.evdokimov.Main;

public class MessagesUtils {
    public static void systemLog(String str) {
        systemLog(str, true);
    }

    public static void systemLog(String str, boolean isUsedPrefix) {
        String prefix = isUsedPrefix ? "&l&8[&a&l" + OtherUtils.systemName + "&l&8]&r " : "";
        String message = format(prefix + str);
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

    public static void sendActionbar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(format(message)));
    }

    public static String format(String arg) {
        return ChatColor.translateAlternateColorCodes('&', arg);
    }

    public static void sendConsoleCommand(String cmd){
        Bukkit.getServer().dispatchCommand(Main.getInstance().console, format(cmd));
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
