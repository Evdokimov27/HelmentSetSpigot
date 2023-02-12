package ru.evdokimov.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import ru.evdokimov.Main;
import ru.evdokimov.utils.ConfigUtils;
import ru.evdokimov.utils.OtherUtils;

public class MainTask {
    public static void setHelmet(){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            for(Player p : Bukkit.getOnlinePlayers()){
                if(p.getInventory().getHelmet() == null){
                    p.getInventory().setHelmet(OtherUtils.getHead(p));
                    continue;
                }
                Material type = p.getInventory().getHelmet().getType();
                if(type == Material.PLAYER_HEAD || type == Material.GOLDEN_HELMET || type == Material.NETHERITE_HELMET || type == Material.DIAMOND_HELMET
                        || type == Material.IRON_HELMET || type == Material.LEATHER_HELMET || type == Material.CARVED_PUMPKIN) continue;
                for(int i = 0; i <= OtherUtils.calc(p, OtherUtils.getHead(p)); i++){
                    p.getInventory().remove(OtherUtils.getHead(p));
                    p.updateInventory();
                }
                if(type == Material.AIR) p.getInventory().setHelmet(OtherUtils.getHead(p));
            }
        }, 0L, ConfigUtils.interval);
    }
}
