package ru.evdokimov;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import ru.evdokimov.events.*;
import ru.evdokimov.utils.*;
import ru.evdokimov.commands.*;

public final class Main extends JavaPlugin {

    private static Main instance;
    public ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    @Override
    public void onEnable() {
        instance = this;

        ConfigUtils.saveConfig(); ConfigUtils.loadConfig();


        this.registerCommands();
        this.registerEvents();
    }


    public static Main getInstance() {
        return instance;
    }


    private void registerCommands() {
        this.getCommand("helmentreload").setExecutor(new ReloadCommand());
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ArmorEquipEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ItemSpawnEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItemEvents(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseEvent(), this);
    }
}
