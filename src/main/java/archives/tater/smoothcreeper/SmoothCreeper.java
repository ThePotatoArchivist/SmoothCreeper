package archives.tater.smoothcreeper;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.WeakHashMap;
import java.util.function.Function;

public class SmoothCreeper implements ModInitializer {
	public static final String MOD_ID = "smoothcreeper";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static <T, R> Function<T, R> weakMemoize(Function<T, R> transform) {
        var cache = new WeakHashMap<T, R>();
        return t -> cache.computeIfAbsent(t, transform);
    }

    public static final Function<RegistryAccess, ItemStack> TOOL = weakMemoize(registryAccess -> {
        var stack = Items.DIAMOND_PICKAXE.getDefaultInstance();
        EnchantmentHelper.updateEnchantments(stack, enchantments ->
            enchantments.set(registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), 1)
        );
        return stack;
    });

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


	}
}