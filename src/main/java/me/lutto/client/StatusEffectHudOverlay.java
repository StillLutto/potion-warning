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

        if (!PotionWarning.getConfigManager().getConfig().enabled) return;
        if (PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect() == null) return;
        drawContext.drawText(MinecraftClient.getInstance().textRenderer,
                PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect().getName().getString() + " has expired!",
                14,
                10,
                config.textColor.getRgb(),
                true);
    }
}