package ru.evdokimov.events;

import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.evdokimov.Main;

public class InventoryCloseEvent implements Listener {
    private SkinsRestorerAPI skinsRestorerAPI = SkinsRestorerAPI.getApi();
    boolean skinAply;
    Main config = Main.getInstance();
    @EventHandler
    public void onInventoryClose(org.bukkit.event.inventory.InventoryCloseEvent e) {
        if (this.skinAply) {
            this.skinAply = false;

            try {
                this.skinsRestorerAPI.applySkin(new PlayerWrapper(e.getPlayer()));
            } catch (SkinRequestException var3) {
                throw new RuntimeException(var3);
            }
        }

    }
}
