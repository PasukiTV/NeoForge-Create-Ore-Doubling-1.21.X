package de.pasuki.create.double_ores.message;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class WelcomeMessageData extends SavedData {
    private static final String DATA_NAME = "create_ore_doubling_welcome";
    private static final String KEY_SHOWN_PLAYERS = "shown_players";
    private static final String KEY_PLAYER_ID = "player_id";

    private final Set<UUID> shownPlayers = new HashSet<>();

    public static WelcomeMessageData load(CompoundTag tag) {
        WelcomeMessageData data = new WelcomeMessageData();
        ListTag list = tag.getList(KEY_SHOWN_PLAYERS, Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag playerEntry = list.getCompound(i);
            if (playerEntry.hasUUID(KEY_PLAYER_ID)) {
                data.shownPlayers.add(playerEntry.getUUID(KEY_PLAYER_ID));
            }
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (UUID uuid : shownPlayers) {
            CompoundTag playerEntry = new CompoundTag();
            playerEntry.putUUID(KEY_PLAYER_ID, uuid);
            list.add(playerEntry);
        }
        tag.put(KEY_SHOWN_PLAYERS, list);
        return tag;
    }

    public static WelcomeMessageData get(ServerLevel overworld) {
        return overworld.getDataStorage().computeIfAbsent(WelcomeMessageData::load, WelcomeMessageData::new, DATA_NAME);
    }

    public boolean wasShown(UUID playerId) {
        return shownPlayers.contains(playerId);
    }

    public void setShown(UUID playerId) {
        if (shownPlayers.add(playerId)) {
            setDirty();
        }
    }
}
