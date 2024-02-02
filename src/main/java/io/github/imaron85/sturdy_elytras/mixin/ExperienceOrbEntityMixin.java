package io.github.imaron85.sturdy_elytras.mixin;

import io.github.imaron85.sturdy_elytras.SturdyElytras;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin {
    @Redirect(method = "repairPlayerGears", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setDamage(I)V"))
    private void setDamage(ItemStack itemStack, int newDamage){
        int sturdynessness = EnchantmentHelper.getLevel(SturdyElytras.STURDYNESS, itemStack);
        if(!(itemStack.getItem() instanceof ElytraItem) || sturdynessness == 0) {
            itemStack.setDamage(newDamage);
            return;
        }

        int i = itemStack.getDamage() - newDamage;
        int newI = i/(sturdynessness * 2);
        if(newI == 0)
            newI = Random.create().nextInt(sturdynessness * 2) == 0 ? 1 : 0;

        itemStack.setDamage(itemStack.getDamage() - newI);
    }
}