package io.github.imaron85.sturdy_elytras;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

public class SturdynessEnchantment extends Enchantment {
	public SturdynessEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentTarget.BREAKABLE, new EquipmentSlot[] { EquipmentSlot.CHEST });
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof ElytraItem;
	}

	@Override
	public int getMinPower(int level) {
		return 5 + (level - 1) * 8;
	}

	@Override
	public int getMaxPower(int level) {
		return this.getMinPower(level) + 20;
	}
	@Override
	public int getMaxLevel() {
		return 5;
	}

	public static boolean shouldPreventDamage(ItemStack item, int level, Random random) {
		if(!(item.getItem() instanceof ElytraItem))
			return false;
		return random.nextInt(level + 1) > 0;
	}
}
