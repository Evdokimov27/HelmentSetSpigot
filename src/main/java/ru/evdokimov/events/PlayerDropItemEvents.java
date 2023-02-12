package ru.evdokimov.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import ru.evdokimov.utils.OtherUtils;

public class PlayerDropItemEvents implements Listener {
    @EventHandler
    public void stopDrop(PlayerDropItemEvent e){
        if(e.getItemDrop().getItemStack().getType() != Material.PLAYER_HEAD) return;
        if(e.getItemDrop().getItemStack() == OtherUtils.getHead(e.getPlayer())){
            e.setCancelled(true);
        }
    }
}
