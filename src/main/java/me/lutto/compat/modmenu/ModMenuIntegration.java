package me.lutto.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.lutto.PotionWarning;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {


            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.of("Potion Warning Config"))
                    .setSavingRunnable(() -> {
                        PotionWarning.getConfigManager().saveConfig();
                        PotionWarning.getConfigManager().loadConfig();
                    });

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            ConfigCategory textCategory = builder.getOrCreateCategory(Text.of("category.potion-warning.general"));
            textCategory.addEntry(entryBuilder.startColorField(Text.of("Text Color"), PotionWarning.getConfigManager().getConfig().textColor)
                    .setDefaultValue(TextColor.fromFormatting(Formatting.RED))
                    .setTooltip(Text.of("Select the text color"))
                    .setSaveConsumer3(newValue -> PotionWarning.getConfigManager().getConfig().textColor = newValue)
                    .build());

            return builder.build();
        };
    }

}
