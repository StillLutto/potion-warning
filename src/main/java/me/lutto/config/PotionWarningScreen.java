package me.lutto.config;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.background.DirtTexturedBackground;
import dev.lambdaurora.spruceui.option.*;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.util.RenderUtil;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import dev.lambdaurora.spruceui.widget.container.tabbed.SpruceTabbedWidget;
import me.lutto.config.hud.HudConfigScreen;
import me.lutto.config.option.SpruceColorOption;
import me.lutto.config.option.SpruceScreenChangeOption;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.*;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;

import static me.lutto.PotionWarning.MOD_ID;
import static net.minecraft.client.util.InputUtil.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.*;


public class PotionWarningScreen extends SpruceScreen {

    private static final Identifier CURSOR = new Identifier("potionwarning",
            "textures/cursor.png");

    private final Screen parent;

    // General options
    private final SpruceOption generalSeparator;
    private final SpruceOption enabled;
    private final SpruceOption warnTime;

    // Text options
    private final SpruceOption textSeparator;
    private final SpruceOption text;
    private final SpruceOption textColor;
    private final SpruceOption textScale;
    private final SpruceOption textShadow;

    // Misc stuff
    private final RotatingCubeMapRenderer background = new RotatingCubeMapRenderer(TitleScreen.PANORAMA_CUBE_MAP);

    public PotionWarningScreen(Screen parent) {
        super(Text.translatable(MOD_ID + ".title"));
        this.parent = parent;

        // General options
        this.generalSeparator = new SpruceSeparatorOption(MOD_ID + ".category.general", true, null);
        this.enabled = new SpruceToggleBooleanOption(MOD_ID + ".option.enabled",
                () -> PotionWarningConfig.enabled,
                value -> PotionWarningConfig.enabled = value,
                Text.translatable(MOD_ID + ".option.enabled.tooltip"));
        this.warnTime = new SpruceIntegerInputOption(MOD_ID + ".option.warn-time",
                () -> PotionWarningConfig.warnTime,
                value -> PotionWarningConfig.warnTime = value,
                Text.translatable(MOD_ID + ".option.warn-time.tooltip"));

        // Text options
        this.textSeparator = new SpruceSeparatorOption(MOD_ID + ".category.text", true, null);
        this.text = new SpruceStringOption(MOD_ID + ".option.text",
                () -> PotionWarningConfig.text,
                value -> PotionWarningConfig.text = value,
                string -> !string.isEmpty(),
                Text.translatable(MOD_ID + ".option.text.tooltip"));
        this.textColor = new SpruceColorOption(MOD_ID + ".option.textColor",
                () -> PotionWarningConfig.textColor,
                value -> PotionWarningConfig.textColor = value);
        this.textScale = new SpruceDoubleOption(MOD_ID + ".option.textScale", 0, 250, 1,
                () -> PotionWarningConfig.textScale,
                value -> PotionWarningConfig.textScale = value, option -> option.getDisplayText(Text.literal(String.valueOf(option.get()))),
                Text.translatable(MOD_ID + ".option.textScale.tooltip"));
        this.textShadow = new SpruceBooleanOption(MOD_ID + ".option.textShadow",
                () -> PotionWarningConfig.textShadow,
                value -> PotionWarningConfig.textShadow = value,
                Text.translatable(MOD_ID + ".option.textShadow.tooltip"),
                true);

        // Status effects options
        for (Identifier id : Registries.STATUS_EFFECT.getIds()) {
            if (PotionWarningConfig.statusEffects.containsKey(id) || Registries.STATUS_EFFECT.get(id).isInstant()) continue;
            PotionWarningConfig.statusEffects.put(id, true);
        }
    }

    @Override
    protected void init() {
        super.init();
        this.buildTabs();

        this.addDrawableChild(this.enabled.createWidget(Position.of(10, this.height - 29), 80));
        this.addDrawableChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 100, this.height - 27), 200, 20, Text.literal("Done"),
                btn -> this.saveChanges()));
    }

    @Override
    public void close() {
        super.close();
        PotionWarningConfig.save();
    }

    @Override
    public void renderBackgroundTexture(DrawContext context) {
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.client.world == null) {
            this.background.render(delta, 1);
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        }

        context.fillGradient(0, 0, width, height, 0x4F141414, 0x4F141414);
        RenderUtil.renderBackgroundTexture(0, 0, this.width, 24, 0);
        RenderUtil.renderBackgroundTexture(0, this.height - 32, this.width, 35, 0);

        super.render(context, mouseX, mouseY, delta);

        context.drawTexture(CURSOR, mouseX - 4, mouseY- 4, 16, 16, 16, 16, 16, 16);
    }

    @Override
    public void renderTitle(DrawContext graphics, int mouseX, int mouseY, float delta) {
        graphics.drawCenteredTextWithShadow(
                this.textRenderer,
                this.title,
                (int)(this.width * 0.5),
                8,
                0xFFFFFF
        );
    }

    public void saveChanges() {
        this.client.setScreen(this.parent);
        PotionWarningConfig.save();
        glfwSetInputMode(MinecraftClient.getInstance().getWindow().getHandle(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    public void buildTabs() {
        var tabs = new SpruceTabbedWidget(Position.of(0, 24), this.width, this.height - 32 - 24,
                null,
                Math.max(116, this.width / 8), 0);
        tabs.getList().setBackground(new DirtTexturedBackground(48, 48, 48, 255));
        this.addDrawableChild(tabs);

        tabs.addSeparatorEntry(Text.translatable(MOD_ID + ".category.general"));
        tabs.addTabEntry(Text.translatable(MOD_ID + ".category.general"), null,
                this::buildGeneralTab);
        tabs.addTabEntry(Text.translatable(MOD_ID + ".category.status-effects"), null,
                this::buildStatusEffectsTab);
    }

    public SpruceOptionListWidget buildGeneralTab(int width, int height) {
        var list = new SpruceOptionListWidget(Position.origin(), width, height);
        list.setBackground(new DirtTexturedBackground(32, 32, 32, 0));
        list.addSingleOptionEntry(this.generalSeparator);
        list.addSingleOptionEntry(this.warnTime);

        list.addSingleOptionEntry(this.textSeparator);
        list.addSingleOptionEntry(this.text);
        list.addSingleOptionEntry(this.textColor);
        list.addSingleOptionEntry(this.textScale);
        list.addSingleOptionEntry(new SpruceScreenChangeOption(MOD_ID + ".option.hud-config-screen", new HudConfigScreen()));
        list.addSingleOptionEntry(this.textShadow);
        return list;
    }

    public SpruceOptionListWidget buildStatusEffectsTab(int width, int height) {
        var list = new SpruceOptionListWidget(Position.origin(), width, height);
        list.setBackground(new DirtTexturedBackground(32, 32, 32, 0));
        for (Identifier id : PotionWarningConfig.statusEffects.keySet()) {
            SpruceOption spruceOption = new SpruceToggleBooleanOption(StringUtils.capitalize(id.getPath()).replace('_', ' '),
                    () -> PotionWarningConfig.statusEffects.get(id),
                    value -> PotionWarningConfig.statusEffects.put(id, value),
                    Text.translatable(id.toTranslationKey()));
            list.addSingleOptionEntry(spruceOption);
        }
        return list;
    }

}
