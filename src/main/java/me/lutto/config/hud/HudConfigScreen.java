package me.lutto.config.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.util.RenderUtil;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import me.lutto.config.PotionWarningConfig;
import me.lutto.config.PotionWarningScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import static net.minecraft.client.util.InputUtil.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.*;

public class HudConfigScreen extends SpruceScreen {

    private boolean renderBorder = false;

    public HudConfigScreen() {
        super(Text.empty());
    }

    private void drawText(DrawContext drawContext, String text, float scale, int x, int y, int color, boolean shadow) {
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

    private boolean isOnText(double mouseX, double mouseY) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        int width = textRenderer.getWidth(PotionWarningConfig.text);
        int height = textRenderer.fontHeight;
        double textPosX = ((PotionWarningConfig.textPosX / 100) * this.width);
        double textPosY = ((PotionWarningConfig.textPosY / 100) * this.height);

        boolean mouseOverX = mouseX >= textPosX && mouseX <= textPosX + width;
        boolean mouseOverY = mouseY >= textPosY && mouseY <= textPosY + height;

        return mouseOverX && mouseOverY;
    }

    private void saveChanges() {
        this.client.setScreen(new PotionWarningScreen(null));
        PotionWarningConfig.save();
        glfwSetInputMode(MinecraftClient.getInstance().getWindow().getHandle(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    @Override
    public void init() {
        super.init();
        glfwSetInputMode(MinecraftClient.getInstance().getWindow().getHandle(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);

        this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 50, 5), 100, 20, Text.literal("Done"),
                btn -> saveChanges()));
    }

    @Override
    public void close() {
        saveChanges();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.client.world == null) {
            RenderUtil.renderBackgroundTexture(0, 0, this.width, this.height, 0);
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        }

        context.fillGradient(0, 0, width, height, 0x4F141414, 0x4F141414);

        super.render(context, mouseX, mouseY, delta);

        context.drawVerticalLine((this.width / 3) * 2, 0, this.height, 0xFFC2C2C2);
        context.drawVerticalLine(this.width / 3, 0, this.height, 0xFFC2C2C2);
        context.drawHorizontalLine(0, this.width, (this.height / 3) * 2, 0xFFC2C2C2);
        context.drawHorizontalLine(0, this.width, this.height / 3, 0xFFC2C2C2);

        String textString = PotionWarningConfig.text.replace("$effect", "Strength");
        float scale = (float) PotionWarningConfig.textScale / 100;
        int textPosX = (int) (this.width * (PotionWarningConfig.textPosX / 100));
        int textPosY = (int) (this.height * (PotionWarningConfig.textPosY / 100));
        boolean shadow = PotionWarningConfig.textShadow;
        drawText(context,
                textString,
                scale,
                textPosX,
                textPosY,
                0xFFFFFFFF,
                shadow);

        if (renderBorder) {
            int width = textRenderer.getWidth(PotionWarningConfig.text);
            int height = textRenderer.fontHeight;
            context.drawBorder(textPosX - 3, textPosY - 3, width + 12, height + 4, 0xFFdb6d6d);
            context.fill(textPosX - 2, textPosY - 3, textPosX + width + 9, textPosY + height, 0x80db6d6d);
        }
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (mouseX < 0 || mouseX > this.width) return false;
        if (mouseY < 0 || mouseY > this.height) return false;
        double previousX = mouseX - deltaX;
        double previousY = mouseY - deltaY;
        double textPosX = ((PotionWarningConfig.textPosX / 100) * this.width);
        double textPosY = ((PotionWarningConfig.textPosY / 100) * this.height);

        if (isOnText(previousX, previousY)) {
            PotionWarningConfig.textPosX = (((textPosX + deltaX) / this.width) * 100);
            PotionWarningConfig.textPosY = ((textPosY + deltaY) / this.height * 100);
            renderBorder = true;
        }

        return false;
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        renderBorder = isOnText(mouseX, mouseY);
    }

}
