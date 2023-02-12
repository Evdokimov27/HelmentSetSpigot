package ru.evdokimov.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.evdokimov.utils.OtherUtils;

public class PlayerJoinEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(!event.getPlayer().hasPlayedBefore()){
            event.getPlayer().getInventory().setHelmet(OtherUtils.getHead(event.getPlayer()));
            event.getPlayer().updateInventory();
        }
    }
}
