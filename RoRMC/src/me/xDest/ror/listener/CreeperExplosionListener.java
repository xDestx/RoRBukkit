package me.xDest.ror.listener;

import java.util.ArrayList;
import java.util.HashMap;

import me.xDest.ror.Messenger;
import me.xDest.ror.Jesus.PrayForJesus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class CreeperExplosionListener implements Listener {

	private JavaPlugin plugin;
	private HashMap<Location, Float> creeperdamage = new HashMap<Location, Float>();
	
	public CreeperExplosionListener(JavaPlugin pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onEntityExplosionPrime(ExplosionPrimeEvent event)
	{
		Entity e = event.getEntity();
		if (e instanceof Creeper)
		{
			Creeper c = (Creeper)e;
			Location l = c.getLocation();
			ArrayList<Player> pir = new ArrayList<Player>();
			//PlayersInRange
			for (Player p : Bukkit.getOnlinePlayers())
			{
				if (p.getLocation().distance(l) <= 15)
				{
					pir.add(p);
				}
			}
			for (final Player p : pir)
			{
				Vector ov = new Vector(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
				Vector ev = new Vector(l.getX(), l.getY(), l.getZ());
				Vector f = ev.subtract(ov).multiply(1.5);
				p.setVelocity(f);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
				{
					@Override
					public void run()
					{
						Vector v = p.getVelocity();
						v = new Vector(v.getX() * -5, Math.abs(v.getY() * -10), v.getZ() * -5);
						p.setVelocity(v);
					}
				},8l);
				
			}
			
		}
	}
	
	@EventHandler
	public void onEntityExplosion(EntityExplodeEvent event) {
		if (event.getEntity() instanceof Creeper) {
			if (!event.getEntity().hasMetadata("explosionpower")) {
				event.setCancelled(true);
				return;
			}
			float p = event.getEntity().getMetadata("explosionpower").get(0).asFloat();
			final Location explloc = event.getLocation();
			//World w = event.getLocation().getWorld();
			event.setCancelled(true);
			creeperdamage.put(explloc, p);
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					creeperdamage.remove(explloc);
				}
				
			}, 10l);
		}
		if (event.getEntity() instanceof Fireball) {
		//	Messenger.info("it was a fireball");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamageEvent(final EntityDamageEvent event) {
		final EntityDamageEvent e = event;
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				if (e.getEntity() instanceof Player) {
					if (e.getCause() == DamageCause.ENTITY_EXPLOSION) {
						Player p = (Player) e.getEntity();
						Location ploc = p.getLocation();
						int d = 10;
						try {
							for (Location l : creeperdamage.keySet()) {
								if ((ploc.getX() <= l.getX() - d && ploc.getX() >= l.getX() + d ) || (ploc.getX() >= l.getX() - d && ploc.getX() <= l.getX() + d )){
									if (ploc.getY() >= l.getY() - d && ploc.getY() <= l.getY() + d) {
										if ((ploc.getZ() <= l.getZ() - d && ploc.getZ() >= l.getZ() + d ) || (ploc.getZ() >= l.getZ() - d && ploc.getZ() <= l.getZ() + d )) {
											double dam = e.getDamage();
											p.damage(dam * creeperdamage.get(l));
											//event.setDamage();
											//event.setDamage(DamageModifier.BASE, dam * 10 * creeperdamage.get(l));
											PrayForJesus.pray(e);
											//event.setDamage();
											//Messenger.broadcast("" + dam + "  " + (dam * creeperdamage.get(l)));
										}
									}
								}
							}
						} catch (NullPointerException er) {
							Messenger.severe("No explosions nearby??");
						}
					}
				}
			}
			
			
		}, 5l);
	}
	
}
