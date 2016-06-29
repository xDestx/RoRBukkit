package me.xDest.ror.listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class BlinkListener implements Listener {
	//On right click
	private JavaPlugin pl;
	private HashMap<UUID,Integer> blinkers;
	
	private static boolean enabled = false;
	
	public static boolean isEnabled()
	{
		return enabled;
	}
	
	public static void setEnabled(boolean b)
	{
		enabled = b;
	}
	
	public BlinkListener(JavaPlugin pl)
	{
		this.pl = pl;
		blinkers = new HashMap<UUID,Integer>();
	}
	
	@EventHandler
	public void playerRightclickListener(final PlayerInteractEvent e)
	{
		if(!BlinkListener.isEnabled())
			return;
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			/*
			 * Attempt 2 
			 */
		//	final BlinkListener self = this;
			if(!blinkers.containsKey(e.getPlayer().getUniqueId()))
				blinkers.put(e.getPlayer().getUniqueId(), new Integer(3));
			if(blinkers.get(e.getPlayer().getUniqueId()).intValue() <= 0)
				return;
			if(e.getPlayer().getItemInHand().getType() == Material.STICK)
			{
				final Location startLocation = e.getPlayer().getLocation();
				decrementPlayerCharge(e.getPlayer());
				informOnAmount(e.getPlayer());
				Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable()
						{
					@Override
					public void run()
					{
						Location endLocation = e.getPlayer().getLocation();
						Vector v = endLocation.subtract(startLocation).toVector();
						if(v.getX() == 0 && v.getY() == 0 && v.getZ() == 0)
						{
							v = e.getPlayer().getLocation().getDirection();
							v = v.setY(0.01);
						}
						if(v.getX() == 0 && v.getY() == 0 && v.getZ() == 0)
							v = new Vector(0,.1,0);
						v = v.setY(v.getY()/4);
						v.normalize();
						v.multiply(15);
						Location x = e.getPlayer().getLocation().add(v);
						e.getPlayer().teleport(x);
						Bukkit.getScheduler().scheduleSyncDelayedTask(pl,new Runnable() {
							@Override
							public void run()
							{
								incrementPlayerCharge(e.getPlayer());
								informOnAmount(e.getPlayer());
							}
						}, 100l);
					}
				}, 1l);
			}
		}
	}
	
	public void decrementPlayerCharge(Player p)
	{
		if(!blinkers.containsKey(p.getUniqueId()))
			return;
		blinkers.put(p.getUniqueId(), new Integer(blinkers.get(p.getUniqueId()).intValue()-1));
	}

	public void incrementPlayerCharge(Player p)
	{
		if(!blinkers.containsKey(p.getUniqueId()))
			return;
		blinkers.put(p.getUniqueId(), new Integer(blinkers.get(p.getUniqueId()).intValue()+1));
	}
	
	private void informOnAmount(Player p)
	{
		if(!blinkers.containsKey(p.getUniqueId()))
			return;
		p.sendMessage("[BLINK] You have " + blinkers.get(p.getUniqueId()).intValue() + "/3 charges");
	}
}
