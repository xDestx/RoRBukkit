package me.xDest.ror;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.xDest.ror.Jesus.PrayForJesus;
import me.xDest.ror.listener.BlinkListener;
import me.xDest.ror.listener.ShiftDropListener;

public class ConfigLoader {

	private static JavaPlugin plugin;
	
	//Jesus enabled
	//
	public static void enable(JavaPlugin pl)
	{
		plugin = pl;
	}
	
	/*
	 * spiderdur
	 * skelm
	 * creeperm
	 * basestr
	 * basespeed
	 * basehp
	 */
	
	public static void saveConfig()
	{
		for (String str : new String[] {"spiderm","skelm","creeperm","basestr","basespeed","basehp"})
		{
			plugin.getConfig().set(str, Constants.get(str));
		}
		plugin.getConfig().set("jesus-enabled", PrayForJesus.isEnabled());
		plugin.getConfig().set("booty-bounce-enabled", ShiftDropListener.isEnabled());
		plugin.getConfig().set("blink-enabled", BlinkListener.isEnabled());
		plugin.saveConfig();
	}
	
	public static void reset()
	{
		Constants.reset();
		ShiftDropListener.setEnabled(false);
		BlinkListener.setEnabled(false);
		saveConfig();
	}
	
	public static void loadConfig()
	{
		FileConfiguration config = plugin.getConfig();
		if(config.contains("spiderm"))
		{
			Constants.spiderdur = config.getInt("spiderm");
		} else {
			reset();
			return;
		}
		if(config.contains("skelm"))
		{
			Constants.skelmulti = config.getInt("skelm");
		} else {
			reset();
			return;
		}
		if(config.contains("creeperm"))
		{
			Constants.creepermulti = config.getInt("creeperm");
		} else {
			reset();
			return;
		}
		if(config.contains("basestr"))
		{
			Constants.basestr = config.getInt("basestr");
		} else {
			reset();
			return;
		}
		if(config.contains("basespeed"))
		{
			Constants.basespeed = config.getInt("basespeed");
		} else {
			reset();
			return;
		}
		if(config.contains("basehp"))
		{
			Constants.basehp = config.getInt("basehp");
		} else {
			reset();
			return;
		}
		if(config.contains("jesus-enabled"))
		{
			PrayForJesus.setEnabled(config.getBoolean("jesus-enabled"));
		} else {
			reset();
			return;
		}
		if(config.contains("booty-bounce-enabled"))
		{
			ShiftDropListener.setEnabled(config.getBoolean("booty-bounce-enabled"));
		} else {
			reset();
			return;
		}
		if(config.contains("blink-enabled"))
		{
			BlinkListener.setEnabled(config.getBoolean("blink-enabled"));
		} else {
			reset();
			return;
		}
	}
	
}
