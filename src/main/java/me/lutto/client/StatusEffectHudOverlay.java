package me.lutto.client;

import me.lutto.config.PotionWarningConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class StatusEffectHudOverlay implements HudRenderCallback {

    private void drawText(DrawContext drawContext, String text, float scale, float x, float y, int color, boolean shadow) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        MatrixStack matrices = drawContext.getMatrices();

        matrices.push();
        matrices.scale(scale, scale, scale);

        textRenderer.draw(
                text,
                x,
                y,
                color,
                shadow,
                matrices.peek().getPositionMatrix(),
                drawContext.getVertexConsumers(),
                TextRenderer.TextLayerType.NORMAL,
                0,
                15728880,
                textRenderer.isRightToLeft());

        matrices.pop();
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (!PotionWarningConfig.enabled) return;
        if (PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect() == null) return;

        String text = PotionWarningConfig.text.replace("$effect", PotionWarningClient.getStatusEffectHudManager().getShowHudStatusEffect().getName().getString());
        float scale = (float) PotionWarningConfig.textScale / 100;
        float x = (float) (drawContext.getScaledWindowWidth() * (PotionWarningConfig.textPosX / 100));
        float y = (float) (drawContext.getScaledWindowHeight() * (PotionWarningConfig.textPosY / 100));
        int color = Color.decode(PotionWarningConfig.textColor).getRGB();
        boolean shadow = PotionWarningConfig.textShadow;

        drawText(
                drawContext,
                text,
                scale,
                x,
                y,
                color,
                shadow);
    }
}