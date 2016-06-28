package me.xDest.ror.listener;


import java.util.Random;

import me.xDest.ror.Constants;
import me.xDest.ror.Messenger;
import me.xDest.ror.PotionManager;
import me.xDest.ror.difficutly.DifficultyTimer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class EntitySpawnListener implements Listener {

	private JavaPlugin pl;
	
	public EntitySpawnListener(JavaPlugin pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e)
	{
		if (e instanceof Player)
			return;
		
		
		final LivingEntity ent = e.getEntity();
		Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable()
		{
			@Override
			public void run()
			{
				ent.setMaxHealth(DifficultyTimer.getDifficulty() * Constants.basehp);
				ent.setHealth(ent.getMaxHealth());
				ent.addPotionEffect(PotionManager.getPotionEffect("INCREASE_DAMAGE", PotionManager.FOREVER, (int) (Constants.basestr * DifficultyTimer.getDifficulty())));
				ent.addPotionEffect(PotionManager.getPotionEffect("SPEED", PotionManager.FOREVER, (int) (Constants.basespeed * DifficultyTimer.getDifficulty())));
				ent.setCanPickupItems(false);
				Entity entity = (Entity) ent;
				entity.setCustomName(entity.getType() + " - " + ChatColor.DARK_RED + (int)ent.getHealth() + ChatColor.WHITE + "/" + ChatColor.RED + (int)ent.getMaxHealth());
				if (entity instanceof Enderman)
				{
					Random r = new Random();
					int x = r.nextInt(100);

					if(DifficultyTimer.getDifficulty() <= 0.25 && DifficultyTimer.getDifficulty() < 0.0 && x <= 10)
					{
							ent.addPotionEffect(PotionManager.getPotionEffect("INVISIBILITY", PotionManager.FOREVER, 1));
					}  else if(DifficultyTimer.getDifficulty() <= 0.5 && DifficultyTimer.getDifficulty() > 0.25 && x <= 30)
					{
							ent.addPotionEffect(PotionManager.getPotionEffect("INVISIBILITY", PotionManager.FOREVER, 1));
					} else if(DifficultyTimer.getDifficulty() <= 0.75 && DifficultyTimer.getDifficulty() > 0.5 && x <= 60)
					{
						ent.addPotionEffect(PotionManager.getPotionEffect("INVISIBILITY", PotionManager.FOREVER, 1));
					} else if (DifficultyTimer.getDifficulty() > 0.75)
					{
						ent.addPotionEffect(PotionManager.getPotionEffect("INVISIBILITY", PotionManager.FOREVER, 1));
					}
				}
				if (entity instanceof Spider)
				{
					Random r = new Random();
					int x = r.nextInt(100);
					if(DifficultyTimer.getDifficulty() <= 0.25 && DifficultyTimer.getDifficulty() < 0.0 && x <= 10)
					{
						Guardian g = (Guardian) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.GUARDIAN);
						((Spider) entity).setPassenger(g);
					} else if(DifficultyTimer.getDifficulty() <= 0.5 && DifficultyTimer.getDifficulty() > 0.25 && x <= 30)
					{
						Guardian g = (Guardian) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.GUARDIAN);
						((Spider) entity).setPassenger(g);
					} else if(DifficultyTimer.getDifficulty() <= 0.75 && DifficultyTimer.getDifficulty() > 0.5 && x <= 60)
					{
						Guardian g = (Guardian) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.GUARDIAN);
						((Spider) entity).setPassenger(g);
					} else if (DifficultyTimer.getDifficulty() > 0.75)
					{
						Guardian g = (Guardian) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.GUARDIAN);
						((Spider) entity).setPassenger(g);
					}
				}
				entity.setCustomNameVisible(true);
			}
		},5l);
		
		setTags(e);
	}
	
	private void setTags(CreatureSpawnEvent e)
	{
		if (e.getEntity() instanceof Creeper)
		{
			Creeper c = (Creeper) e.getEntity();
			c.setMetadata("explosionpower", new FixedMetadataValue(pl, DifficultyTimer.getDifficulty() * Constants.creepermulti));
		} else if (e.getEntity() instanceof Skeleton)
		{
			Skeleton s = (Skeleton) e.getEntity();
			s.setMetadata("iscustom", new FixedMetadataValue(pl, DifficultyTimer.getDifficulty() * Constants.skelmulti));
		} else if (e.getEntity() instanceof Spider)
		{
			Spider s = (Spider) e.getEntity();
			s.setMetadata("isspider", new FixedMetadataValue(pl, DifficultyTimer.getDifficulty() * Constants.spiderdur));
		}
	}
	
}
