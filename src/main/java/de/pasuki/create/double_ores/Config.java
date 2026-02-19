package de.pasuki.create.double_ores;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static {
        BUILDER.push("rawOreCrushing");
    }

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_IRON_EXTRA_DROP_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreCrushing.ironExtraDropChance")
            .defineInRange("ironExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_GOLD_EXTRA_DROP_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreCrushing.goldExtraDropChance")
            .defineInRange("goldExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_COPPER_EXTRA_DROP_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreCrushing.copperExtraDropChance")
            .defineInRange("copperExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_ZINC_EXTRA_DROP_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreCrushing.zincExtraDropChance")
            .defineInRange("zincExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_EXPERIENCE_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreCrushing.experienceChance")
            .defineInRange("experienceChance", 0.75D, 0.0D, 1.0D);

    static {
        BUILDER.pop();
        BUILDER.push("rawOreBlockCrushing");
    }

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_IRON_EXTRA_DROP_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreBlockCrushing.ironExtraDropChance")
            .defineInRange("ironExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_GOLD_EXTRA_DROP_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreBlockCrushing.goldExtraDropChance")
            .defineInRange("goldExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_COPPER_EXTRA_DROP_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreBlockCrushing.copperExtraDropChance")
            .defineInRange("copperExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_ZINC_EXTRA_DROP_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreBlockCrushing.zincExtraDropChance")
            .defineInRange("zincExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_EXPERIENCE_CHANCE = BUILDER
            .translation("create_double_ore.configuration.rawOreBlockCrushing.experienceChance")
            .defineInRange("experienceChance", 0.75D, 0.0D, 1.0D);

    static {
        BUILDER.pop();
    }

    static final ModConfigSpec SPEC = BUILDER.build();
}
