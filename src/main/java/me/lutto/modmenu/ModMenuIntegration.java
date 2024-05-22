package me.lutto.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.lutto.PotionWarning;
import me.lutto.enums.Effects;
import me.lutto.instance.Config;
import me.shedaniel.clothconfig2.api.*;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            Config config = PotionWarning.getConfigManager().getConfig();

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.of("Potion Warning Config"))
                    .setSavingRunnable(() -> {
                        PotionWarning.getConfigManager().saveConfig();
                        PotionWarning.getConfigManager().loadConfig();
                    });

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // General options
            ConfigCategory generalCategory = builder.getOrCreateCategory(Text.of("General Options"));
            generalCategory.addEntry(entryBuilder.startBooleanToggle(Text.of("Enabled"), config.enabled)
                    .setDefaultValue(true)
                    .setTooltip(Text.of("Enable the mod"))
                    .setSaveConsumer(newValue -> config.enabled = newValue)
                    .build());

            // Text subcategory
            SubCategoryBuilder textCategory = entryBuilder.startSubCategory(Text.of("Text Options"));
            textCategory.add(0, entryBuilder.startColorField(Text.of("Text Color"), config.textColor)
                    .setDefaultValue(TextColor.fromFormatting(Formatting.RED))
                    .setTooltip(Text.of("Select the text color"))
                    .setSaveConsumer3(newValue -> config.textColor = newValue)
                    .build());

            // Effect options
            ConfigCategory effectSelectionCategory = builder.getOrCreateCategory(Text.of("Status Effects"));
            for (StatusEffect statusEffect : Registries.STATUS_EFFECT) {
                Effects effect = PotionWarning.getConfigManager().getEffectEnum(statusEffect);
                if (effect == null) continue;

                effectSelectionCategory.addEntry(entryBuilder.startBooleanToggle(statusEffect.getName(), effect.isActivated())
                        .setDefaultValue(true)
                        .setTooltip(Text.of("Toggle " + statusEffect.getName().getString()))
                        .setSaveConsumer(effect::setActivated)
                        .build());
            }

            return builder.build();
        };
    }

}
