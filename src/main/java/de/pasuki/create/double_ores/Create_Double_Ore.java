package de.pasuki.create.double_ores;

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

@Mod(Create_Double_Ore.MOD_ID)
public class Create_Double_Ore {
    public static final String MOD_ID = "create_double_ore";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final ResourceLocation[] RAW_ORE_RECIPES = {
            ResourceLocation.parse("create:crushing/raw_iron"),
            ResourceLocation.parse("create:crushing/raw_gold"),
            ResourceLocation.parse("create:crushing/raw_copper"),
            ResourceLocation.parse("create:crushing/raw_zinc")
    };

    private static final ResourceLocation[] RAW_ORE_BLOCK_RECIPES = {
            ResourceLocation.parse("create:crushing/raw_iron_block"),
            ResourceLocation.parse("create:crushing/raw_gold_block"),
            ResourceLocation.parse("create:crushing/raw_copper_block"),
            ResourceLocation.parse("create:crushing/raw_zinc_block")
    };

    public Create_Double_Ore(IEventBus ignoredModEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        RecipeManager recipeManager = event.getServer().getRecipeManager();

        int updatedEntries = 0;
        int updatedRecipes = 0;

        for (ResourceLocation recipeId : RAW_ORE_RECIPES) {
            int changed = applyConfiguredChances(recipeManager, recipeId, false);
            if (changed >= 0) {
                updatedRecipes++;
                updatedEntries += changed;
            }
        }

        for (ResourceLocation recipeId : RAW_ORE_BLOCK_RECIPES) {
            int changed = applyConfiguredChances(recipeManager, recipeId, true);
            if (changed >= 0) {
                updatedRecipes++;
                updatedEntries += changed;
            }
        }

        LOGGER.info("Config chance patch finished: {} recipes updated, {} outputs changed", updatedRecipes, updatedEntries);
    }

    private static int applyConfiguredChances(RecipeManager recipeManager, ResourceLocation recipeId, boolean isBlockRecipe) {
        RecipeHolder<?> holder = recipeManager.byKey(recipeId).orElse(null);
        if (holder == null) {
            LOGGER.warn("[{}] Recipe not found; skipping chance update", recipeId);
            return -1;
        }

        Object recipe = holder.value();
        List<?> outputs = extractOutputs(recipe);
        if (outputs == null) {
            LOGGER.warn("[{}] Could not access recipe outputs; this may indicate a Create API/internal format change", recipeId);
            return -1;
        }

        String oreName = extractOreName(recipeId);
        float extraDropChance = resolveExtraDropChance(oreName, isBlockRecipe);
        float experienceChance = resolveExperienceChance(isBlockRecipe);

        int updated = 0;
        for (Object output : outputs) {
            Float chance = readChance(output, recipeId);
            ItemStack stack = readStack(output, recipeId);
            if (chance == null || stack == null || stack.isEmpty()) {
                continue;
            }

            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());

            if ("create".equals(itemId.getNamespace()) && "experience_nugget".equals(itemId.getPath())) {
                if (setChance(output, experienceChance, recipeId)) {
                    updated++;
                }
                continue;
            }

            if (itemId.getPath().startsWith("crushed_raw_") && chance < 1.0F) {
                if (setChance(output, extraDropChance, recipeId)) {
                    updated++;
                }
            }
        }

        LOGGER.info("[{}] Applied configured chances to {} output entries", recipeId, updated);
        return updated;
    }

    private static float resolveExtraDropChance(String oreName, boolean isBlockRecipe) {
        if (isBlockRecipe) {
            return switch (oreName) {
                case "iron" -> Config.RAW_ORE_BLOCK_CRUSHING_IRON_EXTRA_DROP_CHANCE.get().floatValue();
                case "gold" -> Config.RAW_ORE_BLOCK_CRUSHING_GOLD_EXTRA_DROP_CHANCE.get().floatValue();
                case "copper" -> Config.RAW_ORE_BLOCK_CRUSHING_COPPER_EXTRA_DROP_CHANCE.get().floatValue();
                case "zinc" -> Config.RAW_ORE_BLOCK_CRUSHING_ZINC_EXTRA_DROP_CHANCE.get().floatValue();
                default -> 0.5F;
            };
        }

        return switch (oreName) {
            case "iron" -> Config.RAW_ORE_CRUSHING_IRON_EXTRA_DROP_CHANCE.get().floatValue();
            case "gold" -> Config.RAW_ORE_CRUSHING_GOLD_EXTRA_DROP_CHANCE.get().floatValue();
            case "copper" -> Config.RAW_ORE_CRUSHING_COPPER_EXTRA_DROP_CHANCE.get().floatValue();
            case "zinc" -> Config.RAW_ORE_CRUSHING_ZINC_EXTRA_DROP_CHANCE.get().floatValue();
            default -> 0.5F;
        };
    }

    private static float resolveExperienceChance(boolean isBlockRecipe) {
        return isBlockRecipe
                ? Config.RAW_ORE_BLOCK_CRUSHING_EXPERIENCE_CHANCE.get().floatValue()
                : Config.RAW_ORE_CRUSHING_EXPERIENCE_CHANCE.get().floatValue();
    }

    private static String extractOreName(ResourceLocation recipeId) {
        String path = recipeId.getPath();
        String prefix = "crushing/raw_";
        if (!path.startsWith(prefix)) {
            return "";
        }

        String oreName = path.substring(prefix.length());
        return oreName.endsWith("_block") ? oreName.substring(0, oreName.length() - 6) : oreName;
    }

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
                return null;
            }
        }
        return null;
    }

    private static ItemStack readStack(Object processingOutput, ResourceLocation recipeId) {
        try {
            return (ItemStack) processingOutput.getClass().getMethod("getStack").invoke(processingOutput);
        } catch (Exception ex) {
            LOGGER.debug("[{}] Could not read processing output stack", recipeId, ex);
            return null;
        }
    }

    private static Float readChance(Object processingOutput, ResourceLocation recipeId) {
        try {
            Object value = processingOutput.getClass().getMethod("getChance").invoke(processingOutput);
            return value instanceof Number n ? n.floatValue() : null;
        } catch (Exception ex) {
            LOGGER.debug("[{}] Could not read processing output chance", recipeId, ex);
            return null;
        }
    }

    private static boolean setChance(Object processingOutput, float newChance, ResourceLocation recipeId) {
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
                LOGGER.warn("[{}] Failed to set processing output chance", recipeId, ex);
                return false;
            }
        }
        return false;
    }
}
