package me.xDest.ror.listener;

import java.util.HashMap;

import me.xDest.ror.Jesus.PrayForJesus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class SkeletonShotListener implements Listener {
	
	JavaPlugin plugin;

	
	
	public SkeletonShotListener(JavaPlugin pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent event) {
		if(event.getEntity() instanceof Skeleton) {
			if (!event.getEntity().hasMetadata("iscustom")) {
				return;
			}
			final float p = event.getEntity().getMetadata("iscustom").get(0).asFloat();
			final Skeleton sk = (Skeleton) event.getEntity();
			final Arrow newArrow = (Arrow) event.getProjectile();
			final Location newArrowL = newArrow.getLocation();
			final Vector newArrowV = newArrow.getVelocity();
			newArrow.setBounce(false);
			newArrow.setCritical(true);
			event.getProjectile().setMetadata("iscustom", new FixedMetadataValue(plugin, p));
			newArrow.setMetadata("iscustom", new FixedMetadataValue(plugin, p));
			final Location evlo = event.getEntity().getLocation();
			final World w = evlo.getWorld();
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					
					for (int i = 0; i < 5; i++) {
						final float pp = p;
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								Arrow a = w.spawnArrow(newArrowL, newArrowV, 1.2f, 4f);
								a.setShooter((ProjectileSource)sk);
								a.setMetadata("iscustom", new FixedMetadataValue(plugin, pp));
								a.setBounce(false);
								a.setCritical(true);
							}
							
							
						}, (7*i));
					}
					
				}
				
			});
		}
		
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		
		if (event.getDamager() instanceof Arrow) {
			Arrow ar = (Arrow) event.getDamager();
			if (!ar.hasMetadata("iscustom"))
				return;
			float p = ar.getMetadata("iscustom").get(0).asFloat();
			Player player = (Player) event.getEntity();
			event.setDamage(event.getDamage() * p);
			PrayForJesus.pray(event);
			ar.remove();
		}
		
	}
	
	@EventHandler
	public void onArrowLand(ProjectileHitEvent event) {
		if (event.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) event.getEntity();
			if (a.getShooter() instanceof Skeleton) {
				a.remove();
			}
		}
	}

}
