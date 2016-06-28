package me.xDest.ror.listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GuardianRainListener implements Listener {

	private JavaPlugin pl;
	
	public GuardianRainListener(JavaPlugin pl) {
		this.pl = pl;
	}
	
	@EventHandler
	public void weatherChange(WeatherChangeEvent e)
	{
		//If true -> raining else -> to dry
		if (e.toWeatherState())
		{
			for (Player p : Bukkit.getOnlinePlayers())
			{
				Random rnd = new Random();
				World w = p.getWorld();
				int x = rnd.nextInt(24) + 1;
				for (int i = 0; i < x; i++)
				{
					w.spawnEntity(new Location(w, p.getLocation().getX() + (rnd.nextBoolean() ? (rnd.nextInt(15)):(-1 * (rnd.nextInt(15)))), p.getLocation().getY() + (rnd.nextInt(15)), p.getLocation().getZ() + (rnd.nextBoolean() ? (rnd.nextInt(15)):(-1 * (rnd.nextInt(15))))), EntityType.GUARDIAN);
				}
			}
		}
		e.setCancelled(true);
	}

}
