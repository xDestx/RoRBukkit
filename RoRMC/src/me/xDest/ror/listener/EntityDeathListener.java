package me.xDest.ror.listener;

import java.util.Random;

import me.xDest.ror.Messenger;
import me.xDest.ror.difficutly.DifficultyTimer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityDeathListener implements Listener {

	private JavaPlugin pl;
	
	public EntityDeathListener(JavaPlugin pl) {
		this.pl = pl;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e)
	{
		if (!(e.getEntity() instanceof LivingEntity))
			return;
		if (e.getEntity() instanceof Player)
		{
			Location l = e.getEntity().getLocation();
			Messenger.broadcast(((Player) e.getEntity()).getDisplayName() + " died at " + (int)l.getX() + ", " + (int)l.getY() + ", " + (int)l.getZ());
			return;
		}
		Random rnd = new Random();
		if (rnd.nextBoolean() == true)
		{
			int MAX = (int) (64 * DifficultyTimer.getDifficulty());
			int amt = rnd.nextInt(MAX + 1);
			e.getDrops().add(new ItemStack(Material.IRON_INGOT, amt));
			
		} else 
		{
			
		}
	}

}
