package me.xDest.ror;

import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class Messenger {

	private static final Logger log = Logger.getLogger("Minecraft");
	private static final String prefix = "[RoR]";
	
	public static void severe(String msg) {
		log.severe(prefix + " " + msg);
	}
	
	public static void info(String msg) {
		log.info(prefix + " " + msg);
	}
	
	public static void broadcast(String msg) {
		Bukkit.broadcastMessage(prefix + " " + msg);
	}
}
