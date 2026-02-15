package de.pasuki.create_ore_doubling;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static {
        BUILDER.comment("Einstellungen für Roh-Erz-Zerkleinerung / Raw ore crushing settings", "Wertebereich 0.0 - 1.0 (0.5 = 50%).")
                .push("rawOreCrushing");
    }

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_IRON_EXTRA_DROP_CHANCE = BUILDER
            .comment("Zusätzliche Crushed-Erz-Chance für Roh-Eisen-Zerkleinerung. / Extra crushed ore chance for raw iron crushing.")
            .translation("create_ore_doubling.configuration.rawOreCrushing.ironExtraDropChance")
            .defineInRange("ironExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_GOLD_EXTRA_DROP_CHANCE = BUILDER
            .comment("Zusätzliche Crushed-Erz-Chance für Roh-Gold-Zerkleinerung. / Extra crushed ore chance for raw gold crushing.")
            .translation("create_ore_doubling.configuration.rawOreCrushing.goldExtraDropChance")
            .defineInRange("goldExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_COPPER_EXTRA_DROP_CHANCE = BUILDER
            .comment("Zusätzliche Crushed-Erz-Chance für Roh-Kupfer-Zerkleinerung. / Extra crushed ore chance for raw copper crushing.")
            .translation("create_ore_doubling.configuration.rawOreCrushing.copperExtraDropChance")
            .defineInRange("copperExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_ZINC_EXTRA_DROP_CHANCE = BUILDER
            .comment("Zusätzliche Crushed-Erz-Chance für Roh-Zink-Zerkleinerung. / Extra crushed ore chance for raw zinc crushing.")
            .translation("create_ore_doubling.configuration.rawOreCrushing.zincExtraDropChance")
            .defineInRange("zincExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_CRUSHING_EXPERIENCE_CHANCE = BUILDER
            .comment("Chance auf Erfahrungsnuggets bei Roh-Erz-Zerkleinerung. / Experience nugget chance for raw ore crushing.")
            .translation("create_ore_doubling.configuration.rawOreCrushing.experienceChance")
            .defineInRange("experienceChance", 0.75D, 0.0D, 1.0D);

    static {
        BUILDER.pop();
        BUILDER.comment("Einstellungen für Roh-Erzblock-Zerkleinerung / Raw ore block crushing settings", "Wertebereich 0.0 - 1.0 (0.5 = 50%).")
                .push("rawOreBlockCrushing");
    }

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_IRON_EXTRA_DROP_CHANCE = BUILDER
            .comment("Zusätzliche Crushed-Erz-Chance für Roh-Eisenblock-Zerkleinerung. / Extra crushed ore chance for raw iron block crushing.")
            .translation("create_ore_doubling.configuration.rawOreBlockCrushing.ironExtraDropChance")
            .defineInRange("ironExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_GOLD_EXTRA_DROP_CHANCE = BUILDER
            .comment("Zusätzliche Crushed-Erz-Chance für Roh-Goldblock-Zerkleinerung. / Extra crushed ore chance for raw gold block crushing.")
            .translation("create_ore_doubling.configuration.rawOreBlockCrushing.goldExtraDropChance")
            .defineInRange("goldExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_COPPER_EXTRA_DROP_CHANCE = BUILDER
            .comment("Zusätzliche Crushed-Erz-Chance für Roh-Kupferblock-Zerkleinerung. / Extra crushed ore chance for raw copper block crushing.")
            .translation("create_ore_doubling.configuration.rawOreBlockCrushing.copperExtraDropChance")
            .defineInRange("copperExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_ZINC_EXTRA_DROP_CHANCE = BUILDER
            .comment("Zusätzliche Crushed-Erz-Chance für Roh-Zinkblock-Zerkleinerung. / Extra crushed ore chance for raw zinc block crushing.")
            .translation("create_ore_doubling.configuration.rawOreBlockCrushing.zincExtraDropChance")
            .defineInRange("zincExtraDropChance", 0.5D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue RAW_ORE_BLOCK_CRUSHING_EXPERIENCE_CHANCE = BUILDER
            .comment("Chance auf Erfahrungsnuggets bei Roh-Erzblock-Zerkleinerung. / Experience nugget chance for raw ore block crushing.")
            .translation("create_ore_doubling.configuration.rawOreBlockCrushing.experienceChance")
            .defineInRange("experienceChance", 0.75D, 0.0D, 1.0D);

    static {
        BUILDER.pop();
    }

    static final ModConfigSpec SPEC = BUILDER.build();
}
