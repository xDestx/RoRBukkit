package me.xDest.ror.difficutly;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.xDest.ror.Messenger;



public class DifficultyTimer {

	private static double difficulty = 0.01;
	//max = 1;
	private static boolean started = false;
	private static boolean init = false;
	//delay in seconds
	private static int delay = 180; 
	
	private static BukkitScheduler bs = Bukkit.getScheduler();
	
	private static JavaPlugin pl;
	
	public static void start(JavaPlugin pl)
	{
		DifficultyTimer.pl = pl;
		if (started || init)
			return;
		started = true;
		init = true;
		
		timerTick(0);
	}
	
	public static void start(JavaPlugin pl, double diff)
	{
		DifficultyTimer.pl = pl;
		if (started || init)
			return;
		started = true;
		init = true;
		
		if (diff < 0.01)
			diff = 0.01;
		difficulty = diff;
		
		timerTick(0);
	}
	
	
	public static void resume()
	{
		if (!init)
		{
			return;
		}
		started = true;
		timerTick(0);
	}
	
	public static void stop()
	{
		started = false;
	}
	
	private static void timerTick(int time)
	{
		final int t = time;
		if (t == -1 || started == false)
			return;
		
		
		if (t >= delay)
		{
			
			difficulty+=0.01;
			
			diffChangeMessage();
			playNaturalDiffSound();
			
			
			bs.scheduleSyncDelayedTask(pl, new Runnable()
			{
				@Override
				public void run()
				{
					timerTick(0);
				}
			},200l);
		} else {
			
			bs.scheduleSyncDelayedTask(pl, new Runnable()
			{
				@Override
				public void run()
				{
					timerTick(t + 10);
				}
			},200l);
		}
	}
	
	public static void diffChangeMessage()
	{
		Messenger.broadcast(ChatColor.GRAY + "New difficulty: " + ChatColor.RED + (int) (difficulty * 100) + "%!");
	}
	
	public static void playNaturalDiffSound()
	{
		for (Player p : Bukkit.getOnlinePlayers())
		{
			final Player p1 = p;
			p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 2.0f, 0.75f);
			p.playSound(p.getLocation(), Sound.ENTITY_GHAST_SCREAM, 2.0f, 0.25f);
			p.getWorld().strikeLightningEffect(p.getLocation());
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable()
			{
				@Override
				public void run()
				{
					p1.playSound(p1.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.75f, 0.01f);
				}
			},20l);
		}
	}
	
	public static int getDelay()
	{
		return delay;
	}
	
	public static void setDelay(int s)
	{
		if (s <= 10)
		{
			return;
		}
		delay = s;
	}
	
	public static void setDifficulty(double d)
	{
		if (d < 0.01)
			d = 0.01;
		difficulty = d;
	}
	
	public static void resetDifficulty()
	{
		difficulty = 0.01;
	}
	
	public static double getDifficulty()
	{
		return difficulty;
	}
}
