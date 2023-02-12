package ru.evdokimov.utils;

import ru.evdokimov.Main;

import java.util.Collections;
import java.util.List;

public class ConfigUtils {
    private static Main pl = Main.getInstance();

    public static String reloaded, headNick, headOwner;
    public static int interval;

    public static void loadConfig() {
        pl.reloadConfig();

        reloaded = getConfigMessage("messages.reloaded");
        headNick = getConfigMessage("settings.head.name");
        interval = getConfigInt("settings.interval");
        headOwner = getConfigMessage("settings.head.owner");
    }

    public static void saveConfig() {
        pl.saveDefaultConfig();
    }

    private static String getConfigMessage(String path) {
        return MessagesUtils.format(pl.getConfig().getString(path));
    }

    private static Boolean getConfigBoolean(String path) {
        return pl.getConfig().getBoolean(path);
    }

    private static int getConfigInt(String path) {
        return pl.getConfig().getInt(path);
    }

    private static List<String> getConfigList(String path) {
        List<String> list = pl.getConfig().getStringList(path);
        String chars = "&";
        Collections.replaceAll(list, chars, MessagesUtils.format(chars));
        return list;
    }
}
