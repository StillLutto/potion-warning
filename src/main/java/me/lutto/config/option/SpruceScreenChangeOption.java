package me.lutto.config.option;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.option.SpruceOption;
import dev.lambdaurora.spruceui.widget.SpruceWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceContainerWidget;
import me.lutto.config.widget.SpruceScreenChangeWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SpruceScreenChangeOption extends SpruceOption {

    private final Screen screen;

    public SpruceScreenChangeOption(String key, Screen screen) {
        super(key);
        this.screen = screen;
    }

    @Override
    public SpruceWidget createWidget(Position position, int width) {
        var container = new SpruceContainerWidget(position, width, 20);
        container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
            var label = new SpruceScreenChangeWidget(Position.of(0, 0),
                    Text.translatable(key),
                    width,
                    containerHeight,
                    screen);
            widgetAdder.accept(label);

        });
        return container;
    }

}
