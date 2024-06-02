package me.lutto.mixin;

import me.lutto.config.PotionWarningScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.util.InputUtil.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.*;

@Mixin(MinecraftClient.class)
public class HideCursorMixin {
    @Inject(at = @At("TAIL"), method = "setScreen")
    private void setScreen(Screen screen, CallbackInfo info) {
        long window = MinecraftClient.getInstance().getWindow().getHandle();
        if (screen instanceof PotionWarningScreen) {
            glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
        }
    }
}
