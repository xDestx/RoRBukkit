package me.xDest.ror.listener;

import me.xDest.ror.PotionManager;


import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SpiderAttackListener implements Listener {

	JavaPlugin plugin;
	
	public SpiderAttackListener(JavaPlugin pl) {
		
		this.plugin = pl;
		
	}
	
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		Entity damaged = e.getEntity();
		if (!(damager instanceof Spider) && !(damaged instanceof Player)) {
			return;
		}
		if (!damager.hasMetadata("isspider")) {
			return;
		}
		Spider spider = (Spider)damager;
		Player player = (Player) damaged;
		int effectdur = spider.getMetadata("isspider").get(0).asInt();
		player.addPotionEffect(PotionManager.getPotionEffect("POISON", 20 * (effectdur), 25));
		player.addPotionEffect(PotionManager.getPotionEffect("WEAKNESS", 20 * (effectdur * 2), 12));
		player.addPotionEffect(PotionManager.getPotionEffect("SLOW", 20 * (effectdur), 5));
	}
	
}
