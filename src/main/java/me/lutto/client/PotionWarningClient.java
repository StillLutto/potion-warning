package me.lutto.client;

import me.lutto.TimerScheduler;
import me.lutto.manager.StatusEffectHudManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class PotionWarningClient implements ClientModInitializer {

    private static final StatusEffectHudManager statusEffectHudManager = new StatusEffectHudManager();
    private static final TimerScheduler scheduler = new TimerScheduler();

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new StatusEffectHudOverlay());
    }

    public static StatusEffectHudManager getStatusEffectHudManager() {
        return statusEffectHudManager;
    }
    public static TimerScheduler getScheduler() {
        return scheduler;
    }

}
