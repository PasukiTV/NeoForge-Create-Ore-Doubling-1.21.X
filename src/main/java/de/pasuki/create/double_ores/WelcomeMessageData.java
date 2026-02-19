package de.pasuki.create.double_ores;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

public class WelcomeMessageData extends SavedData {
    private static final String DATA_NAME = "create_ore_doubling_welcome";
    private static final String KEY_SHOWN = "shown";

    private boolean shown = false;

    public WelcomeMessageData() {}

    // 1.21.x: load hat Provider als Parameter
    public static WelcomeMessageData load(CompoundTag tag, HolderLookup.Provider provider) {
        WelcomeMessageData data = new WelcomeMessageData();
        data.shown = tag.getBoolean(KEY_SHOWN);
        return data;
    }

    // 1.21.x: save hat Provider als Parameter (kein save(CompoundTag) mehr!)
    @Override
    public @NotNull CompoundTag save(CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        tag.putBoolean(KEY_SHOWN, shown);
        return tag;
    }

    /** Wichtig: Overworld nutzen, dann ist es wirklich "pro Welt" */
    public static WelcomeMessageData get(ServerLevel overworld) {
        return overworld.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(WelcomeMessageData::new, WelcomeMessageData::load),
                DATA_NAME
        );
    }

    public boolean wasShown() {
        return shown;
    }

    public void setShown() {
        this.shown = true;
        setDirty();
    }
}
