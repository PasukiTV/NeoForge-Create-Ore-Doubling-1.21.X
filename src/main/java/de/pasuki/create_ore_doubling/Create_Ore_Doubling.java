package de.pasuki.create_ore_doubling;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.List;

@Mod(Create_Ore_Doubling.MOD_ID)
public class Create_Ore_Doubling {
    public static final String MOD_ID = "create_ore_doubling";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Create_Ore_Doubling(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        RecipeManager recipeManager = event.getServer().getRecipeManager();

        applyConfiguredChances(recipeManager, ResourceLocation.parse("create:crushing/raw_iron"), false);
        applyConfiguredChances(recipeManager, ResourceLocation.parse("create:crushing/raw_gold"), false);
        applyConfiguredChances(recipeManager, ResourceLocation.parse("create:crushing/raw_copper"), false);
        applyConfiguredChances(recipeManager, ResourceLocation.parse("create:crushing/raw_zinc"), false);

        applyConfiguredChances(recipeManager, ResourceLocation.parse("create:crushing/raw_iron_block"), true);
        applyConfiguredChances(recipeManager, ResourceLocation.parse("create:crushing/raw_gold_block"), true);
        applyConfiguredChances(recipeManager, ResourceLocation.parse("create:crushing/raw_copper_block"), true);
        applyConfiguredChances(recipeManager, ResourceLocation.parse("create:crushing/raw_zinc_block"), true);
    }

    private static void applyConfiguredChances(RecipeManager recipeManager, ResourceLocation recipeId, boolean isBlockRecipe) {
        RecipeHolder<?> holder = recipeManager.byKey(recipeId).orElse(null);
        if (holder == null) {
            LOGGER.warn("Recipe {} not found; skipping chance update", recipeId);
            return;
        }

        Object recipe = holder.value();
        List<?> outputs = extractOutputs(recipe);
        if (outputs == null) {
            LOGGER.warn("Could not access outputs for recipe {}; skipping", recipeId);
            return;
        }

        float extraDropChance = (float) (isBlockRecipe
                ? Config.RAW_ORE_BLOCK_EXTRA_DROP_CHANCE.get()
                : Config.RAW_ORE_EXTRA_DROP_CHANCE.get());
        float experienceChance = (float) Config.EXPERIENCE_NUGGET_CHANCE.get();

        int updated = 0;
        for (Object output : outputs) {
            Float chance = readChance(output);
            ItemStack stack = readStack(output);
            if (chance == null || stack == null || stack.isEmpty()) {
                continue;
            }

            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
            if (itemId == null) {
                continue;
            }

            if ("create".equals(itemId.getNamespace()) && "experience_nugget".equals(itemId.getPath())) {
                if (setChance(output, experienceChance)) {
                    updated++;
                }
                continue;
            }

            if (itemId.getPath().startsWith("crushed_raw_") && chance < 1.0F) {
                if (setChance(output, extraDropChance)) {
                    updated++;
                }
            }
        }

        LOGGER.info("Applied configured chance values to {} output entries in recipe {}", updated, recipeId);
    }

    @SuppressWarnings("unchecked")
    private static List<?> extractOutputs(Object recipe) {
        Class<?> type = recipe.getClass();
        while (type != null) {
            try {
                Field resultsField = type.getDeclaredField("results");
                resultsField.setAccessible(true);
                return (List<?>) resultsField.get(recipe);
            } catch (NoSuchFieldException ignored) {
                type = type.getSuperclass();
            } catch (IllegalAccessException ex) {
                LOGGER.warn("Failed to access recipe results", ex);
                return null;
            }
        }
        return null;
    }

    private static ItemStack readStack(Object processingOutput) {
        try {
            return (ItemStack) processingOutput.getClass().getMethod("getStack").invoke(processingOutput);
        } catch (Exception ex) {
            return null;
        }
    }

    private static Float readChance(Object processingOutput) {
        try {
            Object value = processingOutput.getClass().getMethod("getChance").invoke(processingOutput);
            return value instanceof Number n ? n.floatValue() : null;
        } catch (Exception ex) {
            return null;
        }
    }

    private static boolean setChance(Object processingOutput, float newChance) {
        Class<?> type = processingOutput.getClass();
        while (type != null) {
            try {
                Field chanceField = type.getDeclaredField("chance");
                chanceField.setAccessible(true);
                chanceField.setFloat(processingOutput, newChance);
                return true;
            } catch (NoSuchFieldException ignored) {
                type = type.getSuperclass();
            } catch (Exception ex) {
                LOGGER.warn("Failed to set processing output chance", ex);
                return false;
            }
        }
        return false;
    }
}
