package ru.evdokimov.events;

import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import ru.evdokimov.Main;
import ru.evdokimov.utils.OtherUtils;

import java.util.Iterator;

public class PlayerDeathEvents implements Listener {
    private SkinsRestorerAPI skinsRestorerAPI = SkinsRestorerAPI.getApi();
    Main config = Main.getInstance();
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        for(ItemStack item : event.getDrops()){
            if(item.getType() != Material.PLAYER_HEAD) continue;
            if(!item.hasItemMeta()) continue;
            if(item.getItemMeta() != OtherUtils.getHead(event.getEntity()).getItemMeta()) continue;
            event.getDrops().remove(item);
        }
    }
    @EventHandler
    public void onDeaths(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Iterator<ItemStack> drops = e.getDrops().iterator();

        while(drops.hasNext()) {
            ItemStack drop = (ItemStack)drops.next();
            if (drop.getType() == Material.PLAYER_HEAD) {
                e.getDrops().remove(drop);

                try {
                    this.skinsRestorerAPI.setSkin(e.getEntity().getName(), config.getConfig().getString("skin.0.name"));
                    this.skinsRestorerAPI.applySkin(new PlayerWrapper(e.getEntity()));
                } catch (SkinRequestException var6) {
                    throw new RuntimeException(var6);
                }
            } else if (e.getEntity().getInventory().getHelmet().getItemMeta().isUnbreakable()) {
                try {
                    this.skinsRestorerAPI.setSkin(e.getEntity().getName(), config.getConfig().getString("skin." + e.getEntity().getInventory().getHelmet().getItemMeta().getCustomModelData() + ".name"));
                    this.skinsRestorerAPI.applySkin(new PlayerWrapper(e.getEntity().getName()));
                } catch (SkinRequestException var8) {
                    throw new RuntimeException(var8);
                }
            } else if (drop.getType() == Material.CARVED_PUMPKIN) {
                try {
                    this.skinsRestorerAPI.setSkin(e.getEntity().getName(), config.getConfig().getString("skin.0.name"));
                    this.skinsRestorerAPI.applySkin(new PlayerWrapper(e.getEntity()));
                } catch (SkinRequestException var7) {
                    throw new RuntimeException(var7);
                }
            }
        }

    }
}
