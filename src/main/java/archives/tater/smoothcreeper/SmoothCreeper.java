package archives.tater.smoothcreeper;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import net.minecraft.world.item.enchantment.providers.VanillaEnchantmentProviders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmoothCreeper implements ModInitializer {
	public static final String MOD_ID = "smoothcreeper";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ResourceKey<EnchantmentProvider> CREEPER_LOOT_DROP = ResourceKey.create(Registries.ENCHANTMENT_PROVIDER, id("creeper_loot_drop"));

    public static ItemStack getTool(ServerLevel level, Entity creeper) {
        var stack = Items.DIAMOND_PICKAXE.getDefaultInstance();

        EnchantmentHelper.enchantItemFromProvider(stack, level.registryAccess(), VanillaEnchantmentProviders.ENDERMAN_LOOT_DROP, level.getCurrentDifficultyAt(creeper.blockPosition()), creeper.getRandom());

        return stack;
    }

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


	}
}