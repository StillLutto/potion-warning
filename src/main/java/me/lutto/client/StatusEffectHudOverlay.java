package me.lutto.client;

import me.lutto.config.PotionWarningConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.Nullable;

public class StatusEffectHudOverlay implements HudRenderCallback {

    private void drawText(DrawContext drawContext, TextRenderer textRenderer, String text, int x, int y, int color, boolean shadow, boolean centeredText) {
        if (centeredText) {
            x = x - textRenderer.getWidth(text) / 2;
        }

        textRenderer.draw(text,
                x,
                y,
                color,
                shadow,
                drawContext.getMatrices().peek().getPositionMatrix(),
                drawContext.getVertexConsumers(),
                TextRenderer.TextLayerType.NORMAL,
                0,
                15728880,
                textRenderer.isRightToLeft());
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        PotionWarningConfig config = AutoConfig.getConfigHolder(PotionWarningConfig.class).getConfig();

        if (!config.enabled) return;
        if (PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect() == null) return;

        String text = config.text.replace("$effect", PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect().getName().getString());
        int x = (int) (drawContext.getScaledWindowWidth() * ((double) config.textPosX / 100));
        int y = (int) (drawContext.getScaledWindowHeight() * ((double) config.textPosY / 100));
        int color = config.textColor;
        boolean shadow = config.textShadow;
        boolean centeredText = config.centeredText;

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        drawText(drawContext,
                textRenderer,
                text,
                x,
                y,
                color,
                shadow,
                centeredText);
    }
}