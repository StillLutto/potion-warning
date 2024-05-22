package me.lutto.client;

import me.lutto.PotionWarning;
import me.lutto.instance.Config;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class StatusEffectHudOverlay implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        Config config = PotionWarning.getConfigManager().getConfig();

        if (!config.enabled) return;
        if (PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect() == null) return;
        if (config.centeredText) {
            drawContext.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,
                    PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect().getName().getString() + " has expired!",
                    (int) (drawContext.getScaledWindowWidth() * ((double) config.textPosX / 100)),
                    (int) (drawContext.getScaledWindowHeight() * ((double) config.textPosY / 100)),
                    config.textColor.getRgb());
        } else {
            drawContext.drawText(MinecraftClient.getInstance().textRenderer,
                    PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect().getName().getString() + " has expired!",
                    (int) (drawContext.getScaledWindowWidth() * ((double) config.textPosX / 100)),
                    (int) (drawContext.getScaledWindowHeight() * ((double) config.textPosY / 100)),
                    config.textColor.getRgb(),
                    true);
        }
    }
}