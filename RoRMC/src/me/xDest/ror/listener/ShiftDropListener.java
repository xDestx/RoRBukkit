package me.xDest.ror.listener;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class ShiftDropListener implements Listener {

	private JavaPlugin pl;
	private ArrayList<UUID> bootyDroppinPlayers;
	
	public ShiftDropListener(JavaPlugin pl)
	{
		this.pl  = pl;
		bootyDroppinPlayers = new ArrayList<UUID>();
	}
	
	@EventHandler
	public void playerInitiateBootyDrop(PlayerToggleSneakEvent e)
	{
		if(e.getPlayer().isSneaking() || bootyDroppinPlayers.contains(e.getPlayer().getUniqueId()))
			return;
		World w = e.getPlayer().getWorld();
		double playerY = e.getPlayer().getLocation().getY();
		double highestY = w.getHighestBlockYAt(e.getPlayer().getLocation());
		
		if(playerY - highestY >= 2.5)
		{
			//Booty drop
			e.getPlayer().setVelocity(new Vector(0,-3,0));
			e.getPlayer().sendMessage("Initiate booty");
			bootyDroppinPlayers.add(e.getPlayer().getUniqueId());
		}
	}
	
	@EventHandler
	public void playerBootyDropEvent(PlayerMoveEvent e)
	{
		if(!bootyDroppinPlayers.contains(e.getPlayer().getUniqueId()))
			return;
		
		if(e.getPlayer().isOnGround())
		{
			//BOOTY DROP AHHHH
			//Why would something like this be deprecated
			double per = Math.random();
			
			double damage = per * (30 * 2);//Hearts of damage
			for(Entity ent : e.getPlayer().getNearbyEntities(3, 1, 3))
			{
				if(ent instanceof LivingEntity)
				{
					((LivingEntity)ent).damage(damage, e.getPlayer());
					if(ent instanceof Player)
					{
						((Player)ent).sendMessage("BOOTY BOUNCED");
					}
				}
			}
			bootyDroppinPlayers.remove(e.getPlayer().getUniqueId());
		}
				
	}
	
	
}
