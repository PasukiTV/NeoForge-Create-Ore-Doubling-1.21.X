package de.pasuki.create_ore_doubling;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue RAW_ORE_EXTRA_DROP_CHANCE = BUILDER
            .comment("Chance for extra crushed ore output from raw ore crushing recipes.")
            .defineInRange("rawOreExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_EXTRA_DROP_CHANCE = BUILDER
            .comment("Chance for each extra crushed ore output from raw ore block crushing recipes.")
            .defineInRange("rawOreBlockExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue EXPERIENCE_NUGGET_CHANCE = BUILDER
            .comment("Chance for experience nugget outputs from ore crushing recipes.")
            .defineInRange("experienceNuggetChance", 0.75D, 0.0D, 1.0D);

    static final ModConfigSpec SPEC = BUILDER.build();
}
