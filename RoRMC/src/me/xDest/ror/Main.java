package me.xDest.ror;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.xDest.ror.Jesus.PrayForJesus;
import me.xDest.ror.crafting.CraftingManager;
import me.xDest.ror.crafting.CustomCraftingListener;
import me.xDest.ror.difficutly.DifficultyTimer;
import me.xDest.ror.listener.BlinkListener;
import me.xDest.ror.listener.CreeperExplosionListener;
import me.xDest.ror.listener.EntityDamageListener;
import me.xDest.ror.listener.EntityDeathListener;
import me.xDest.ror.listener.EntitySpawnListener;
import me.xDest.ror.listener.GuardianRainListener;
import me.xDest.ror.listener.ShiftDropListener;
import me.xDest.ror.listener.SkeletonShotListener;
import me.xDest.ror.listener.SpiderAttackListener;

public class Main extends JavaPlugin {

	/*
	 * To do:
	 *     - Create difficulty timer x
	 *     - Make mobs increase in hp, spd, and str as difficulty increases x
	 *     - Add custom crafting for stronger items
	 *     - (Hell storms?)
	 */
	public Main getThis()
	{
		return this;
	}
	
	@Override
	public void onEnable()
	{
		Messenger.info("Starting RoR");
		DifficultyTimer.start(this);
		Messenger.info("Timer Started");
		CraftingManager.enable();
		Messenger.info("Crafting Enabled");
		PrayForJesus.enable(this);
		Messenger.info("Jesus enabled");
		Bukkit.getServer().getPluginManager().registerEvents(new EntitySpawnListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CustomCraftingListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EntityDeathListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CreeperExplosionListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SkeletonShotListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SpiderAttackListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GuardianRainListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ShiftDropListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BlinkListener(this), this);
		
	}
	
	
	@Override
	public void onDisable()
	{
		Messenger.info("Stopping RoR");
		DifficultyTimer.stop();
		Messenger.info("Timer Stopped");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			if (label.equals("diff"))
			{
				Messenger.info("The difficulty is " + (int) (DifficultyTimer.getDifficulty() * 100) + "%");
				return true;
			} else if (label.equals("setdiff") && args.length == 1)
			{
				try
				{
					double nd = Double.parseDouble(args[0]);
					DifficultyTimer.setDifficulty(nd);
					Messenger.info("Set difficulty to " + DifficultyTimer.getDifficulty());
					return true;
				}catch(Exception e)
				{
					Messenger.info("Invalid number..." + args[0]);
					return false;
				}
			} else if (label.equals("pause"))
			{
				DifficultyTimer.stop();
				Messenger.info("Timer paused.");
				return true;
			} else if (label.equals("start"))
			{
				DifficultyTimer.resume();
				Messenger.info("Timer resumed.");
				return true;
			}
			return false;
		}
		Player p = (Player) sender;
		if (label.equals("diff"))
		{
			p.sendMessage(ChatColor.DARK_RED + "The difficulty is " + ChatColor.GOLD + (int) (DifficultyTimer.getDifficulty() * 100) + "%");
			return true;
		} else if (label.equals("setdiff") && args.length == 1)
		{
			try
			{
				double nd = Double.parseDouble(args[0]);
				DifficultyTimer.setDifficulty(nd);
				p.sendMessage(ChatColor.GREEN + "Set difficulty to " + DifficultyTimer.getDifficulty());
				DifficultyTimer.diffChangeMessage();
				DifficultyTimer.playNaturalDiffSound();
				return true;
			}catch(Exception e)
			{
				p.sendMessage(ChatColor.RED + "Invalid number..." + args[0]);
				return false;
			}
		} else if (label.equals("pause"))
		{
			DifficultyTimer.stop();
			p.sendMessage(ChatColor.RED + "Timer paused.");
			return true;
		} else if (label.equals("start"))
		{
			DifficultyTimer.resume();
			p.sendMessage(ChatColor.GREEN + "Timer resumed.");
			return true;
		} else if (label.equals("setdelay") && args.length ==1)
		{
			try
			{
				int nd = Integer.parseInt(args[0]);
				DifficultyTimer.setDelay(nd);
				p.sendMessage(ChatColor.GREEN + "Set delay to " + DifficultyTimer.getDelay());
				return true;
			}catch(Exception e)
			{
				p.sendMessage(ChatColor.RED + "Invalid int..." + args[0]);
				return false;
			}
		} else if (label.equals("delay"))
		{
			p.sendMessage(ChatColor.GREEN + "The current difficulty delay is " + DifficultyTimer.getDelay() + "s.");
			return true;
		} else if (label.equals("annihilate"))
		{
			p.sendMessage(ChatColor.DARK_RED + "HOLY SHITTTTTTT BOOOOM");
			blowStuffUp(p);
			return true;
		}
		
		return false;
	}
	
	
	
	public void blowStuffUp(final Player p)
	{
		for (int i = 0; i < 10; i++)
		{
			final int k = i;
			Bukkit.getScheduler().scheduleSyncDelayedTask(getThis(), new Runnable(){
				@Override
				public void run()
				{
					p.getWorld().spawnEntity(p.getLocation().add(k*2, 5, 0), EntityType.PRIMED_TNT);
					p.getWorld().spawnEntity(p.getLocation().add(0, 5, k*2), EntityType.PRIMED_TNT);
					p.getWorld().spawnEntity(p.getLocation().add(k*2, 5, k*2), EntityType.PRIMED_TNT);
					p.getWorld().spawnEntity(p.getLocation().add(0, 5,(-1 * k * 2)), EntityType.PRIMED_TNT);
					p.getWorld().spawnEntity(p.getLocation().add((-1 * k * 2), 5, 0), EntityType.PRIMED_TNT);
					p.getWorld().spawnEntity(p.getLocation().add((-1 * k * 2), 5, (-1 * k *2)), EntityType.PRIMED_TNT);
				}
			}, 25l);
					
		}
	}

}
