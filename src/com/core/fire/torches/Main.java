package com.core.fire.torches;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created with IntelliJ IDEA.
 * User: MayoDwarf
 * Date: 6/30/14
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main extends JavaPlugin implements Listener {
    private FileConfiguration settings;
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        this.settings = this.getConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
    }
    public void onDisable() {}
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity ent = e.getEntity();
        Entity damager = e.getDamager();
        if(damager instanceof Player) {
            Player p = (Player) damager;
            if(p.getItemInHand() !=null) {
                if(p.getItemInHand().getType() == Material.TORCH) {
                    ent.setFireTicks(this.settings.getInt("FireTicks")*20);
                }
            }
        }
    }
}
