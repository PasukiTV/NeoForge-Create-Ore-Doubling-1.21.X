package de.pasuki.create.double_ores.message;

import de.pasuki.create.double_ores.Create_Double_Ore;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Create_Double_Ore.MOD_ID)
public final class WelcomeMessageEvents {
    private static final String GITHUB_URL = "https://github.com/PasukiTV/NeoForge-Create-Double-Ores-1.21.1/issues";
    private static final String DISCORD_URL = "https://discord.gg/9y97PyeD6s";

    @SubscribeEvent
    public static void onPlayerJoined(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ServerLevel overworld = player.server.overworld();
        WelcomeMessageData data = WelcomeMessageData.get(overworld);
        var playerId = player.getUUID();

        if (!data.wasShown(playerId)) {
            player.sendSystemMessage(
                    Component.translatable("create_double_ore.welcome.title")
                            .withStyle(ChatFormatting.GRAY)
            );

            player.sendSystemMessage(
                    Component.translatable("create_double_ore.welcome.feedback_intro")
                            .withStyle(ChatFormatting.DARK_GRAY)
                            .append(Component.literal(" "))
                            .append(link("create_double_ore.welcome.feedback.github", GITHUB_URL))
                            .append(Component.literal(" | ").withStyle(ChatFormatting.DARK_GRAY))
                            .append(link("create_double_ore.welcome.feedback.discord", DISCORD_URL))
            );

            data.setShown(playerId);
        }
    }

    private static Component link(String key, String url) {
        return Component.translatable(key)
                .withStyle(style -> style
                        .withColor(ChatFormatting.AQUA)
                        .withUnderlined(true)
                        .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url))
                );
    }
}