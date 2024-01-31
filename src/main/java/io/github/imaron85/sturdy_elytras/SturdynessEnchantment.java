package io.github.imaron85.sturdy_elytras;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;

public class SturdynessEnchantment extends Enchantment {
	public SturdynessEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentTarget.BREAKABLE, new EquipmentSlot[] { EquipmentSlot.CHEST });
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof ElytraItem;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}
}
