package me.xDest.ror.listener;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class IronListener implements Listener {

	private JavaPlugin pl;
	
	public IronListener(JavaPlugin pl)
	{
		this.pl= pl;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		Random rnd = new Random();
		int x = rnd.nextInt(99);
		if (x >= 10 && x <= 14) {
			p.getInventory().addItem(new ItemStack(Material.IRON_ORE));
		}
		if (rnd.nextInt(10000) == 22)
		{
			for (int i = 0; i < 4; i++)
			{
				p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.IRON_BLOCK, 64));
			}
		}
	}
	
}
