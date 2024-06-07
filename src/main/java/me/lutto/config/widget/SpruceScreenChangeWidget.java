package me.lutto.config.widget;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SpruceScreenChangeWidget extends SpruceButtonWidget {

    private final Screen screen;

    public SpruceScreenChangeWidget(Position position, Text text, int width, int height, Screen screen) {
        super(position, width, height, text, null);
        this.screen = screen;
    }

    @Override
    public void onPress() {
        this.client.setScreen(screen);
    }

}
