package io.github.imaron85.sturdy_elytras.mixin;

import io.github.imaron85.sturdy_elytras.SturdyElytras;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.resources.ResourceLocation;

@Mixin(ExperienceOrb.class)
public abstract class ExperienceOrbEntityMixin extends Entity {

    @Unique
    private static final TagKey<Item> STURDYNESS_APPLICABLE = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(SturdyElytras.MOD_ID, "sturdyness_applicable")
    );

    public ExperienceOrbEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    // CHANGED: method = "repairPlayerGears" -> method = "repairPlayerItems"
    @Redirect(method = "repairPlayerItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;setDamageValue(I)V"))
    private void sturdyElytras$modifyMendingRepair(ItemStack stack, int newDamage) {
        // 1. Check if the item is applicable (e.g. Elytra)
        if (!stack.is(STURDYNESS_APPLICABLE)) {
            stack.setDamageValue(newDamage);
            return;
        }

        // 2. Get Registry Access
        var registry = this.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        var enchantmentEntry = registry.get(SturdyElytras.STURDYNESS);

        // 3. Safety Check
        if (enchantmentEntry.isEmpty()) {
            stack.setDamageValue(newDamage);
            return;
        }

        // 4. Check Level
        int level = EnchantmentHelper.getItemEnchantmentLevel(enchantmentEntry.get(), stack);
        if (level == 0) {
            stack.setDamageValue(newDamage);
            return;
        }

        // 5. Apply Custom Mending Logic
        int currentDamage = stack.getDamageValue();
        int repairAmount = currentDamage - newDamage;

        // Calculate reduced repair amount
        int divisor = 1 << level;
        int reducedRepair = repairAmount / divisor;

        // Handle small repair amounts using random chance
        if (reducedRepair == 0) {
            reducedRepair = this.random.nextInt(divisor) == 0 ? 1 : 0;
        }

        // Apply final damage
        stack.setDamageValue(currentDamage - reducedRepair);
    }
}