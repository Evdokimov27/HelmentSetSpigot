package ru.evdokimov.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import ru.evdokimov.utils.OtherUtils;

public class ItemSpawnEvents implements Listener {
    @EventHandler
    public void onSpawn(ItemSpawnEvent e){
        if(e.getEntity().getItemStack().getType() != Material.PLAYER_HEAD) return;
        if(!e.getEntity().getItemStack().hasItemMeta())
        for(Player player : Bukkit.getOnlinePlayers()){
            if(e.getEntity().getItemStack().getItemMeta() == OtherUtils.getHead(player).getItemMeta()){
                e.setCancelled(true);
            }
        }
    }
}
