# Create Ore Doubling (NeoForge 1.21.1)

Create: Double Ores expands the crushing process in the [Create](https://www.curseforge.com/minecraft/mc-mods/create) mod to make raw ores more rewarding.
By feeding raw ores or raw ore blocks into Create's Crushing Wheels, you receive guaranteed crushed outputs with a configurable chance for additional bonus materials.

This introduces ore doubling (and beyond) directly into the Create workflow, making automation lines more efficient and satisfying.

## CurseForge Description (updated)

### ✨ Features

- New crushing recipes for **Raw Iron, Raw Copper, Raw Gold, and Raw Zinc** (items and raw ore blocks).
- Guaranteed crushed ore baseline:
  - **1 crushed ore** per raw ore item
  - **9 crushed ores** per raw ore block
- Configurable bonus crushed ores (default: **up to +50%**), preserving Create-style progression.
- Bonus **Experience Nuggets** as byproducts (item and block recipes use separate config values).
- Full config support for each ore type and for raw ore blocks, so modpacks can tune balance precisely.
- In-game configuration localization for **English** and **German**.

### ✅ Compatibility

- Minecraft **1.21.1**
- NeoForge **21.1.x**
- Create **6.0.6+** (within configured version range)

### 🔧 Requires

- Create

### 📦 Modpacks

- Allowed

### 🏭 Why Use It?

Whether you are building fully automated ore processing factories or just want to squeeze more value out of mining trips,
Create: Double Ores makes resources go further and enhances the satisfaction of watching massive Crushing Wheels grind raw materials into riches.

### 🐛 Feedback

If you encounter bugs or have feature requests, please open an issue or leave feedback in the project channel.

## Configuration

The config is organized by recipe groups:

- `rawOreCrushing.*`
  - `ironExtraDropChance`
  - `goldExtraDropChance`
  - `copperExtraDropChance`
  - `zincExtraDropChance`
  - `experienceChance`
- `rawOreBlockCrushing.*`
  - `ironExtraDropChance`
  - `goldExtraDropChance`
  - `copperExtraDropChance`
  - `zincExtraDropChance`
  - `experienceChance`

Config path (server/client):

- `config/create_ore_doubling-common.toml`

All values use `0.0` to `1.0` (`0.5 = 50%`, `0.75 = 75%`).

## Development

### Run client

```bash
./gradlew runClient
```

### Build

```bash
./gradlew build
```

## Notes

This project started from the NeoForge MDK template and was adapted into a Create addon focused on ore processing.
