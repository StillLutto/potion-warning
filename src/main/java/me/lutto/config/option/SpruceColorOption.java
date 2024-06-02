package me.lutto.config.option;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.option.SpruceOption;
import dev.lambdaurora.spruceui.widget.SpruceLabelWidget;
import dev.lambdaurora.spruceui.widget.SpruceWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceContainerWidget;
import dev.lambdaurora.spruceui.widget.text.SpruceTextFieldWidget;

import me.lutto.PotionWarning;
import me.lutto.config.PotionWarningConfig;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;

public class SpruceColorOption extends SpruceOption {

    private final Supplier<String> getter;
    private final Consumer<String> setter;

    public SpruceColorOption(String key, Supplier<String> getter, Consumer<String> setter) {
        super(key);
        this.getter = getter;
        this.setter = setter;
    }

    @Override
    public SpruceWidget createWidget(Position position, int width) {
        var container = new SpruceContainerWidget(position, width, 20);
        container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
            var label = new SpruceLabelWidget(Position.of(0, 0),
                    Text.literal(""),
                    width - 80);
            widgetAdder.accept(label);

            var text = new SpruceTextFieldWidget(Position.of(0, 0), containerWidth - 30, containerHeight, Text.translatable(key));
            text.setText(getter.get());

            var preview = new SpruceButtonWidget(Position.of(containerWidth - 20, 0),
                    20, containerHeight,
                    Text.literal("⬛").setStyle(Style.EMPTY.withColor(Color.decode(PotionWarningConfig.textColor).getRGB())),
                    x -> {});
            widgetAdder.accept(preview);

            text.setChangedListener(value -> {
                try {
                    preview.setMessage(Text.literal("⬛").setStyle(Style.EMPTY.withColor(Color.decode(value).getRGB())));
                } catch (NumberFormatException e) {
                    PotionWarning.getLogger().warn("Not a hex color!");
                    return;
                }
                setter.accept(value);
            });

            widgetAdder.accept(text);
        });
        return container;
    }

}
