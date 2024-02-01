package io.github.imaron85.sturdy_elytras.mixin;

import io.github.imaron85.sturdy_elytras.SturdyElytras;
import io.github.imaron85.sturdy_elytras.SturdynessEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@ModifyVariable(method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z", at = @At("STORE"), ordinal = 0)
	private int adjustAmount(int amount){
		ItemStack thisObject = (ItemStack)(Object)this;

		if(!(thisObject.getItem() instanceof ElytraItem))
			return amount;

		Random random = Random.create();

		int i = EnchantmentHelper.getLevel(SturdyElytras.STURDYNESS, thisObject);
		int j = 0;

		for(int k = 0; i > 0 && k < amount; ++k) {
			if (SturdynessEnchantment.shouldPreventDamage(thisObject, i, random)) {
				++j;
			}
		}

		amount -= j;

		return amount;
	}
}
