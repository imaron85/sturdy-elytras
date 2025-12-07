package io.github.imaron85.sturdy_elytras;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries; // Moved from net.minecraft.registry
import net.minecraft.resources.ResourceKey;      // Replaces RegistryKey
import net.minecraft.resources.ResourceLocation; // Replaces Identifier
import net.minecraft.world.item.enchantment.Enchantment; // Moved package
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SturdyElytras implements ModInitializer {
    public static final String MOD_ID = "sturdy_elytras";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // RegistryKey is now ResourceKey in MojMap
    public static final ResourceKey<Enchantment> STURDYNESS = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "sturdyness")
    );

    @Override
    public void onInitialize() {
        // Data-driven; no manual registration needed.
    }
}