package me.xDest.ror.listener;

import java.util.Random;

import me.xDest.ror.Messenger;
import me.xDest.ror.Jesus.PrayForJesus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityDamageListener implements Listener {

	private JavaPlugin pl;
	
	public EntityDamageListener(JavaPlugin pl) {
		this.pl = pl;
	}

	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e)
	{
		if ((e.getEntity() instanceof LivingEntity) && !(e.getEntity() instanceof Player))
		{
			LivingEntity ent = (LivingEntity) e.getEntity();
			
			if (ent.getHealth() <= 0)
			{
				return;
			}
			
			Entity entity = e.getEntity();
			entity.setCustomName(entity.getType() + " - " + ChatColor.DARK_RED + (int)ent.getHealth() + ChatColor.WHITE + "/" + ChatColor.RED + (int)ent.getMaxHealth());
			entity.setCustomNameVisible(true);
		} if (e.getEntity() instanceof Player)
		{
			PrayForJesus.pray(e);
		} else {
			return;
		}
	}
}
