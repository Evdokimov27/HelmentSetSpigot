package ru.evdokimov.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClickEvents implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getInventory().getType() != InventoryType.CRAFTING) return;
        if(event.getSlotType() != InventoryType.SlotType.ARMOR) return;
        if(event.getSlot() == 5){
            if(event.getCurrentItem().getType() == Material.PLAYER_HEAD){
                event.setCancelled(true);
                Player p = (Player) event.getWhoClicked();
                p.updateInventory();
            }
        }
    }
}
