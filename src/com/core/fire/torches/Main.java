package com.core.fire.torches;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
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
    public void onEnable() {                                                  //Re-commit
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
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();
        Location block = to.subtract(0, 1, 0);
        if(to.getX() > from.getX() || to.getZ() > from.getZ() || to.getY() > from.getY() || to.getX() < from.getX() || to.getZ() < from.getZ() || to.getY() < from.getY()) {
            p.sendBlockChange(from.subtract(0, 1, 0), from.getBlock().getType(), from.getBlock().getData());
            p.sendBlockChange(from, from.getBlock().getType(), from.getBlock().getData());
            p.sendBlockChange(from, from.getBlock().getType(), from.getBlock().getData());
        }
        if(!block.getBlock().getType().equals(Material.AIR) && block.getBlock().getData() == (byte) 0) {
            if(p.getItemInHand() !=null) {
               if(p.getItemInHand().getType() == Material.TORCH) {
                    p.sendBlockChange(block, Material.GLOWSTONE, (byte) 0);
               }
            }
        }
    }
}
