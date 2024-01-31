package io.github.imaron85.sturdy_elytras;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SturdyElytras implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("sturdy_elytras");

	public static Enchantment STURDYNESS;

	@Override
	public void onInitialize() {
		STURDYNESS = Registry.register(Registries.ENCHANTMENT, "sturdy_elytras:sturdyness", new SturdynessEnchantment());
	}
}
