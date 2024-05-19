package me.lutto.client;

import me.lutto.HudDisplayHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class PotionWarningClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((DrawContext drawContext, float tickDelta) -> {
            if (HudDisplayHandler.shouldDisplayHud()) {
                drawContext.drawText(MinecraftClient.getInstance().textRenderer, "Status Effect Expired!", 10, 10, 0xFF0F0F, true);
            }
        });
    }

}
