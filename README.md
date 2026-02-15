# Create Ore Doubling (NeoForge 1.21.1)

Create Ore Doubling is a lightweight addon for the [Create](https://www.curseforge.com/minecraft/mc-mods/create) mod.
It adds and balances crushing recipes so raw ores and raw ore blocks produce extra crushed ore outputs.

## Features

- Crushing recipes for raw **iron**, **gold**, **copper**, and **zinc**.
- Crushing recipes for corresponding **raw ore blocks**.
- Extra outputs are chance-based to preserve Create-style progression and automation balancing.

## Compatibility

- Minecraft **1.21.1**
- NeoForge **21.1.x**
- Create **6.0.6+** (within the configured range)


## Configuration

The mod exposes drop chances in the common config file:

- `rawOreExtraDropChance`
- `rawOreBlockExtraDropChance`
- `experienceNuggetChance`

Config path (server/client):

- `config/create_ore_doubling-common.toml`

Values are expected between `0.0` and `1.0`.

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
