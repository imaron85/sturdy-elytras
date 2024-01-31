package io.github.imaron85.sturdy_elytras.mixin;

import io.github.imaron85.sturdy_elytras.SturdyElytras;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z"))
	public boolean damage(ItemStack stack, int amount, Random random, @Nullable ServerPlayerEntity player){
		SturdyElytras.LOGGER.info("DAMAGE ITEM");
		return stack.damage(amount, random, player);
	}
}
