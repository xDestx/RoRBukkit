package me.xDest.ror.crafting;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.xDest.ror.Messenger;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;


public class CustomCraftingListener implements Listener {

	JavaPlugin plugin;

	final Material[] helmets = {Material.LEATHER_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.DIAMOND_HELMET};
	final Material[] chests = {Material.LEATHER_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.DIAMOND_CHESTPLATE};
	final Material[] legs = {Material.LEATHER_LEGGINGS, Material.GOLD_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_LEGGINGS};
	final Material[] boots = {Material.LEATHER_BOOTS, Material.GOLD_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.DIAMOND_BOOTS};
	final Material[] swords = {Material.STONE_SWORD, Material.GOLD_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD, Material.WOOD_SWORD};
	final Material[] pickaxes = {Material.STONE_PICKAXE, Material.WOOD_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};
	final Material[] shovels = {Material.STONE_SPADE, Material.WOOD_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE};
	final Material[] axes = {Material.STONE_AXE, Material.WOOD_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE};
	final Material[] hoes = {Material.STONE_HOE, Material.WOOD_HOE, Material.IRON_HOE, Material.GOLD_HOE, Material.DIAMOND_HOE};
	final Material[] bows = {Material.BOW};
	
	public CustomCraftingListener(JavaPlugin pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void prepareCraftEvent(PrepareItemCraftEvent e) {
		if (e.isRepair()) {
			Messenger.severe("@CustomCraft: Triggered by repair");
			return;
		}
		checkHelm(e);
		checkChest(e);
		checkLegs(e);
		checkBoots(e);
		checkSwords(e);
		checkPicks(e);
		checkAxes(e);
		checkShovels(e);
		checkHoes(e);
		checkBows(e);
	}
	
	private void checkHelm(PrepareItemCraftEvent e) {
		ItemStack item = e.getInventory().getItem(5);
		for (Material helmet : helmets) {
			ItemStack result = new ItemStack(helmet, 1);
			if (item == null)
				return;
			if (item.getType() != helmet) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					result.addUnsafeEnchantment(en, iench.get(en) + 1);
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
		
	}
	
	private void checkChest(PrepareItemCraftEvent e) {
		ItemStack item = e.getInventory().getItem(5);
		for (Material chest : chests) {
			ItemStack result = new ItemStack(chest, 1);
			if (item == null)
				return;
			if (item.getType() != chest) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					if (iench.get(en) >= 127) {
						
					} else {
						result.addUnsafeEnchantment(en, iench.get(en) + 1);
					}
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
		
	}
	
	private void checkLegs(PrepareItemCraftEvent e) {
		ItemStack item = e.getInventory().getItem(5);
		for (Material leg : legs) {
			ItemStack result = new ItemStack(leg, 1);
			if (item == null)
				return;
			if (item.getType() != leg) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					if (iench.get(en) >= 127) {

					} else {
						result.addUnsafeEnchantment(en, iench.get(en) + 1);
					}
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
		
	}
	
	private void checkBoots(PrepareItemCraftEvent e) {
		ItemStack item = e.getInventory().getItem(5);
		for (Material boot : boots) {
			ItemStack result = new ItemStack(boot, 1);
			if (item == null)
				return;
			if (item.getType() != boot) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					if (iench.get(en) >= 127) {

					} else {
						result.addUnsafeEnchantment(en, iench.get(en) + 1);
					}
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
		
	}
	
	private void checkSwords(PrepareItemCraftEvent e) {
		ItemStack item = e.getInventory().getItem(5);
		for (Material sword : swords) {
			ItemStack result = new ItemStack(sword, 1);
			if (item == null)
				return;
			if (item.getType() != sword) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					result.addUnsafeEnchantment(en, iench.get(en) + 1);
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
		
	}
	
	private void checkPicks(PrepareItemCraftEvent e) {
		ItemStack item = e.getInventory().getItem(5);
		for (Material pick : pickaxes) {
			ItemStack result = new ItemStack(pick, 1);
			if (item == null)
				return;
			if (item.getType() != pick) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					result.addUnsafeEnchantment(en, iench.get(en) + 1);
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
		
	}
	
	private void checkAxes(PrepareItemCraftEvent e) {
		ItemStack item = e.getInventory().getItem(5);
		for (Material axe : axes) {
			ItemStack result = new ItemStack(axe, 1);
			if (item == null)
				return;
			if (item.getType() != axe) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					result.addUnsafeEnchantment(en, iench.get(en) + 1);
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
		
	}
	
	private void checkShovels(PrepareItemCraftEvent e) {
		ItemStack item = e.getInventory().getItem(5);
		for (Material spade : shovels) {
			ItemStack result = new ItemStack(spade, 1);
			if (item == null)
				return;
			if (item.getType() != spade) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					result.addUnsafeEnchantment(en, iench.get(en) + 1);
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
		
	}
	
	private void checkHoes(PrepareItemCraftEvent e) {
		ItemStack item = e.getInventory().getItem(5);
		for (Material hoe : hoes) {
			ItemStack result = new ItemStack(hoe, 1);
			if (item == null)
				return;
			if (item.getType() != hoe) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					result.addUnsafeEnchantment(en, iench.get(en) + 1);
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
		
	}
	
	private void checkBows(PrepareItemCraftEvent e)
	{
		ItemStack item = e.getInventory().getItem(5);
		for (Material bow : bows) {
			ItemStack result = new ItemStack(bow, 1);
			if (item == null)
				return;
			if (item.getType() != bow) {
				
			} else {
				Map<Enchantment, Integer> iench = item.getEnchantments();
				if (iench.isEmpty()) {
					return;
				}
				for (Enchantment en : iench.keySet()) {
					result.addUnsafeEnchantment(en, iench.get(en) + 1);
					//Messenger.broadcast("Added enchantment " + en + " level: " + (iench.get(en) + 1));
				}
				e.getInventory().setResult(result);
			}
		}
	}
	
	
}
