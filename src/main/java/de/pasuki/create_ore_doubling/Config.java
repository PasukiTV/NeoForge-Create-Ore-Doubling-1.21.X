package de.pasuki.create_ore_doubling;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static {
        BUILDER.comment("Raw Ore Crushing recipe settings", "Values are 0.0 - 1.0 where 0.5 = 50%.")
                .push("rawOreCrushing");
    }

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_IRON_EXTRA_DROP_CHANCE = BUILDER
            .comment("Extra crushed ore chance for raw iron crushing.")
            .defineInRange("ironExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_GOLD_EXTRA_DROP_CHANCE = BUILDER
            .comment("Extra crushed ore chance for raw gold crushing.")
            .defineInRange("goldExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_COPPER_EXTRA_DROP_CHANCE = BUILDER
            .comment("Extra crushed ore chance for raw copper crushing.")
            .defineInRange("copperExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_ZINC_EXTRA_DROP_CHANCE = BUILDER
            .comment("Extra crushed ore chance for raw zinc crushing.")
            .defineInRange("zincExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_EXPERIENCE_CHANCE = BUILDER
            .comment("Experience nugget chance for raw ore crushing.")
            .defineInRange("experienceChance", 0.75D, 0.0D, 1.0D);

    static {
        BUILDER.pop();
        BUILDER.comment("Raw Ore Block Crushing recipe settings", "Values are 0.0 - 1.0 where 0.5 = 50%.")
                .push("rawOreBlockCrushing");
    }

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_IRON_EXTRA_DROP_CHANCE = BUILDER
            .comment("Extra crushed ore chance for raw iron block crushing.")
            .defineInRange("ironExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_GOLD_EXTRA_DROP_CHANCE = BUILDER
            .comment("Extra crushed ore chance for raw gold block crushing.")
            .defineInRange("goldExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_COPPER_EXTRA_DROP_CHANCE = BUILDER
            .comment("Extra crushed ore chance for raw copper block crushing.")
            .defineInRange("copperExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_ZINC_EXTRA_DROP_CHANCE = BUILDER
            .comment("Extra crushed ore chance for raw zinc block crushing.")
            .defineInRange("zincExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_EXPERIENCE_CHANCE = BUILDER
            .comment("Experience nugget chance for raw ore block crushing.")
            .defineInRange("experienceChance", 0.75D, 0.0D, 1.0D);

    static {
        BUILDER.pop();
    }

    static final ModConfigSpec SPEC = BUILDER.build();
}
