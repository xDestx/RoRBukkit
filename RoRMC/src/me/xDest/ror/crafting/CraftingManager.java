package me.xDest.ror.crafting;

import java.util.ArrayList;
import java.util.List;

import me.xDest.ror.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.MaterialData;

public class CraftingManager {
	
	private static List<ShapedRecipe> recipes = new ArrayList<ShapedRecipe>();
	final static Material[] helmets = {Material.LEATHER_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.DIAMOND_HELMET};
	final static Material[] chests = {Material.LEATHER_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.DIAMOND_CHESTPLATE};
	final static Material[] legs = {Material.LEATHER_LEGGINGS, Material.GOLD_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_LEGGINGS};
	final static Material[] boots = {Material.LEATHER_BOOTS, Material.GOLD_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.DIAMOND_BOOTS};
	final static Material[] swords = {Material.STONE_SWORD, Material.GOLD_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD, Material.WOOD_SWORD};
	final static Material[] pickaxes = {Material.STONE_PICKAXE, Material.WOOD_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};
	final static Material[] shovels = {Material.STONE_SPADE, Material.WOOD_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE};
	final static Material[] axes = {Material.STONE_AXE, Material.WOOD_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE};
	final static Material[] hoes = {Material.STONE_HOE, Material.WOOD_HOE, Material.IRON_HOE, Material.GOLD_HOE, Material.DIAMOND_HOE};
	final static Material[] bows = {Material.BOW};
	final static ItemStack iron = new ItemStack(Material.IRON_INGOT, 1);
	final static MaterialData md = iron.getData();
	
	private static boolean doneonce = false;
	
	public static void enable() {
		//Creating all recipes
		createArmor();
		createSwords();
		createTools();
		createDiamond();
		createOthers();
		createBow();
		doneonce = true;
	}
	
	private static void createArmor() {
		if (!doneonce) {
			
			
			for (Material helmet : helmets) {
				ItemStack helm1 = new ItemStack(helmet, 1);
				helm1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				helm1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe newhelmet = new ShapedRecipe(helm1);
				newhelmet.shape("***","*%*","***");
				newhelmet.setIngredient('*', md);
				newhelmet.setIngredient('%', helmet);
				Bukkit.getServer().addRecipe(newhelmet);
			}
			for (Material chest : chests) {
				ItemStack chest1 = new ItemStack(chest, 1);
				chest1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				chest1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe newchest = new ShapedRecipe(chest1);
				newchest.shape("***","*%*","***");
				newchest.setIngredient('*', md);
				newchest.setIngredient('%', chest);
				Bukkit.getServer().addRecipe(newchest);
			}
			for (Material leg : legs) {
				ItemStack leg1 = new ItemStack(leg, 1);
				leg1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				leg1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe newleg = new ShapedRecipe(leg1);
				newleg.shape("***","*%*","***");
				newleg.setIngredient('*', md);
				newleg.setIngredient('%', leg);
				Bukkit.getServer().addRecipe(newleg);
			}
			for (Material boot : boots) {
				ItemStack boot1 = new ItemStack(boot, 1);
				boot1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				boot1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe newboot = new ShapedRecipe(boot1);
				newboot.shape("***","*%*","***");
				newboot.setIngredient('*', md);
				newboot.setIngredient('%', boot);
				Bukkit.getServer().addRecipe(newboot);
			}
		}
	}
	
	private static void createSwords() {
		if (!doneonce) {
			for (Material sword : swords) {
				ItemStack sword1 = new ItemStack(sword, 1);
				sword1.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
				sword1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe newsword = new ShapedRecipe(sword1);
				newsword.shape("***","*%*","***");
				newsword.setIngredient('*', md);
				newsword.setIngredient('%', sword);
				Bukkit.getServer().addRecipe(newsword);
			}
		}
	}
	
	private static void createDiamond()
	{
		if (!doneonce)
		{
			ItemStack diamond1 = new ItemStack(Material.DIAMOND, 2);
			ShapedRecipe diamond = new ShapedRecipe(diamond1);
			diamond.shape("*%*","%#%","*%*");
			diamond.setIngredient('*', Material.IRON_BLOCK);
			diamond.setIngredient('%', Material.FLINT_AND_STEEL);
			diamond.setIngredient('#', Material.COAL);
			Bukkit.getServer().addRecipe(diamond);
		}
	}
	
	private static void createOthers()
	{
		if (!doneonce)
		{
			ItemStack flint1 = new ItemStack(Material.FLINT, 1);
			ShapelessRecipe flint = new ShapelessRecipe(flint1);
			flint.addIngredient(Material.GRAVEL);
			Bukkit.getServer().addRecipe(flint);
			//////
			ItemStack coal1 = new ItemStack(Material.COAL, 1);
			ShapelessRecipe coal = new ShapelessRecipe(coal1);
			coal.addIngredient(new ItemStack(Material.COAL, 1, (short)1).getData());
			Bukkit.getServer().addRecipe(coal);
			//////
			
		}
	}
	
	private static void createBow()
	{
		if (!doneonce)
		{
			for (Material bow : bows) {
				ItemStack bow1 = new ItemStack(bow, 1);
				bow1.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
				bow1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe bown = new ShapedRecipe(bow1);
				bown.shape("***","*%*","***");
				bown.setIngredient('*', md);
				bown.setIngredient('%', bow);
				Bukkit.getServer().addRecipe(bown);
			}
		}
	}
	
	private static void createTools()
	{
		if (!doneonce) {
			for (Material pick : pickaxes) {
				ItemStack pick1 = new ItemStack(pick, 1);
				pick1.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
				pick1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe newpick = new ShapedRecipe(pick1);
				newpick.shape("***","*%*","***");
				newpick.setIngredient('*', md);
				newpick.setIngredient('%', pick);
				Bukkit.getServer().addRecipe(newpick);
			}
			
			for (Material spade : shovels) {
				ItemStack spade1 = new ItemStack(spade, 1);
				spade1.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
				spade1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe newspade = new ShapedRecipe(spade1);
				newspade.shape("***","*%*","***");
				newspade.setIngredient('*', md);
				newspade.setIngredient('%', spade);
				Bukkit.getServer().addRecipe(newspade);
			}
			
			for (Material hoe : hoes) {
				ItemStack hoe1 = new ItemStack(hoe, 1);
				hoe1.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
				hoe1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe newhoe = new ShapedRecipe(hoe1);
				newhoe.shape("***","*%*","***");
				newhoe.setIngredient('*', md);
				newhoe.setIngredient('%', hoe);
				Bukkit.getServer().addRecipe(newhoe);
			}
			
			for (Material axe : axes) {
				ItemStack axe1 = new ItemStack(axe, 1);
				axe1.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
				axe1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				ShapedRecipe newaxe = new ShapedRecipe(axe1);
				newaxe.shape("***","*%*","***");
				newaxe.setIngredient('*', md);
				newaxe.setIngredient('%', axe);
				Bukkit.getServer().addRecipe(newaxe);
			}
		}
		
	}
	
	
}
