package me.lutto.client;

import me.lutto.config.PotionWarningConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class StatusEffectHudOverlay implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        PotionWarningConfig config = AutoConfig.getConfigHolder(PotionWarningConfig.class).getConfig();

        if (!config.enabled) return;
        if (PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect() == null) return;

        String text = config.text.replace("$effect", PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect().getName().getString());
        int textPosX = (int) (drawContext.getScaledWindowWidth() * ((double) config.textPosX / 100));
        int textPosY = (int) (drawContext.getScaledWindowHeight() * ((double) config.textPosY / 100));
        int textColor = config.textColor;

        if (config.centeredText) {
            drawContext.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,
                    text,
                    textPosX,
                    textPosY,
                    textColor);
        } else {
            drawContext.drawText(MinecraftClient.getInstance().textRenderer,
                    text,
                    textPosX,
                    textPosY,
                    config.textColor,
                    true);
        }
    }
}