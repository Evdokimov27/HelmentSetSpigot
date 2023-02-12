package ru.evdokimov.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import ru.evdokimov.Main;

public class OtherUtils {
    public static String systemName = Main.getInstance().getDescription().getName();
    public static String version = Main.getInstance().getDescription().getVersion();



    public static ItemStack getHead(Player player){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        assert meta != null;
        if(!ConfigUtils.headNick.equalsIgnoreCase("none")){
            meta.setDisplayName(Main.getInstance().getConfig().getString("settings.head.name").replace("{player}", player.getName()));
        }

        meta.setOwningPlayer(player);
        if(!ConfigUtils.headOwner.equalsIgnoreCase("none")){
            meta.setOwner(ConfigUtils.headOwner);
        }

        item.setItemMeta(meta);
        return item;
    }

    public static int calc(Player p, ItemStack s) {
        int count = 0;
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            ItemStack stack = p.getInventory().getItem(i);
            if (stack == null)
                continue;
            if (stack.isSimilar(s)) {
                count += stack.getAmount();
            }
        }
        return count;
    }
}
