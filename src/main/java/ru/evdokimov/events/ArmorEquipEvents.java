package ru.evdokimov.events;

import lowbrain.armorequip.ArmorEquipEvent;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.evdokimov.Main;
import ru.evdokimov.utils.OtherUtils;

public class ArmorEquipEvents implements Listener {
    private SkinsRestorerAPI skinsRestorerAPI = SkinsRestorerAPI.getApi();
    boolean skinAply;
    Main config = Main.getInstance();

    @EventHandler
    public void equip(ArmorEquipEvent e) {
        Player p = e.getPlayer();
        if (e.getNewArmorPiece().getType() != Material.PLAYER_HEAD && e.getNewArmorPiece().getType() == Material.CARVED_PUMPKIN) {
            for(int model = 0; model < config.getConfig().getInt("max_model"); ++model) {
                if (e.getNewArmorPiece().getItemMeta() == null) {
                    return;
                }

                if (e.getNewArmorPiece().getItemMeta().getCustomModelData() == model) {
                    Long time = 0L;

                    try {
                        this.skinsRestorerAPI.setSkin(p.getName(), config.getConfig().getString("skin." + model + ".name"));
                    } catch (SkinRequestException var7) {
                        var7.printStackTrace();
                    }
                }
            }
        }

        if (e.getOldArmorPiece().getType() != Material.AIR && e.getNewArmorPiece().getType() != Material.CARVED_PUMPKIN && e.getNewArmorPiece().getType() != Material.PLAYER_HEAD) {
            if (e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR) {
                return;
            }

            try {
                this.skinsRestorerAPI.setSkin(p.getName(), config.getConfig().getString("skin.0.name"));
            } catch (SkinRequestException var6) {
                throw new RuntimeException(var6);
            }
        }

    }
    @EventHandler
    public void Check(ArmorEquipEvent e) {
        if (e.getOldArmorPiece().getType() != e.getNewArmorPiece().getType()) {
            this.skinAply = true;
        }
        if (e.getNewArmorPiece().hasItemMeta() && e.getOldArmorPiece().hasItemMeta()) {
            if (e.getNewArmorPiece().getItemMeta().hasCustomModelData() && e.getOldArmorPiece().getItemMeta().hasCustomModelData()) {
                if (e.getNewArmorPiece().getItemMeta().getCustomModelData() != e.getOldArmorPiece().getItemMeta().getCustomModelData()) {
                    this.skinAply = true;
                }
            }
        }
    }
    @EventHandler
    public void onEquip(ArmorEquipEvent event){
        if(event.getNewArmorPiece() == null){
            event.setCancelled(true);
            event.getPlayer().updateInventory();
            return;
        }
        if(event.getOldArmorPiece() == null){
            return;
        }
        Material newType = event.getNewArmorPiece().getType();
        Material oldType = event.getOldArmorPiece().getType();
        if(newType == Material.AIR & oldType == Material.PLAYER_HEAD) {
            event.setCancelled(true);
            event.getPlayer().updateInventory();
            return;
        } else if(oldType == Material.PLAYER_HEAD & (newType == Material.GOLDEN_HELMET || newType == Material.DIAMOND_HELMET || newType == Material.NETHERITE_HELMET
        | newType == Material.IRON_HELMET || newType == Material.LEATHER_HELMET || newType == Material.CARVED_PUMPKIN)){
            event.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
            event.getPlayer().updateInventory();
            return;
        }
    }
}
