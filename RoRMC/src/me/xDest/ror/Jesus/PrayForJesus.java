package me.xDest.ror.Jesus;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.plugin.java.JavaPlugin;

import me.xDest.ror.Main;
import me.xDest.ror.Messenger;

public class PrayForJesus {

	private static boolean enabled = false;
	private static JavaPlugin pl;
	
	public static void init(JavaPlugin pl)
	{
		PrayForJesus.pl = pl;
	}
	
	public static void setEnabled(boolean b)
	{
		if(pl == null)
			return;
		enabled = b;
	}
	
	public static boolean isEnabled()
	{
		return enabled;
	}
	
	public static void pray(EntityDamageEvent e)
	{
		if (!enabled)
			return;
		Random r = new Random();
		final Player p = (Player) e.getEntity();
		if (p.getHealth() - e.getDamage(DamageModifier.BASE) > 0)
		{
			return;
		}
		if (r.nextBoolean())
		{
			p.sendMessage(ChatColor.GOLD + "Jesus has saved you! You have 5 seconds to flee before you're returned.");
			e.setCancelled(true);
			p.setHealth(p.getMaxHealth());
			
			p.setGameMode(GameMode.SPECTATOR);
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				
				@Override
				public void run()
				{
					p.setGameMode(GameMode.SURVIVAL);
				}
				
			}, 100l);
			
			//Fancy stuff?
			p.getWorld().strikeLightningEffect(p.getLocation());
			
			for (int i = 0; i < 20; i++)
			{
				long delay = (i * 20);
				delay /= 4;
				final int x = i;
				Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable()
				{
				
					@Override
					public void run()
					{
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, (float) (x * .1));
					}
					
				}, delay);
			//	Messenger.info("" + delay);
			}
		} else {
			
		}
	}

}
