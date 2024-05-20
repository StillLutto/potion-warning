package me.lutto.client;

import me.lutto.TimerScheduler;
import me.lutto.manager.StatusEffectHudManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class PotionWarningClient implements ClientModInitializer {

    private static final StatusEffectHudManager statusEffectHudManager = new StatusEffectHudManager();
    private static final TimerScheduler scheduler = new TimerScheduler();

    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register((DrawContext drawContext, float tickDelta) -> {
            if (statusEffectHudManager.getShowHudStatusEffect() != null) {
                drawContext.drawText(MinecraftClient.getInstance().textRenderer, statusEffectHudManager.getShowHudStatusEffect().getName().getString() + " has expired!", 10, 10, 0xFF0F0F, true);
            }
        });
    }

    public static StatusEffectHudManager getStatusEffectHudManager() {
        return statusEffectHudManager;
    }
    public static TimerScheduler getScheduler() {
        return scheduler;
    }

}
