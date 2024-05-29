package me.lutto.manager;

import me.lutto.annotations.StatusEffects;
import me.lutto.instance.PotionWarningConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static me.shedaniel.autoconfig.util.Utils.getUnsafely;

public class ConfigManager {
    private static final ConfigEntryBuilder ENTRY_BUILDER = ConfigEntryBuilder.create();

    private PotionWarningConfig config;

    private void registerAnnotations() {
        GuiRegistry registry = AutoConfig.getGuiRegistry(PotionWarningConfig.class);

        registry.registerAnnotationProvider(
                (i18n, field, registryConfig, defaults, guiProvider) -> {
                    Set<Identifier> value = getUnsafely(field, registryConfig);
                    List<Identifier> statusEffects = new ArrayList<>();
                    for (Identifier statusEffectId : Registries.STATUS_EFFECT.getIds()) {
                        for (Identifier id : value) {
                            if (statusEffectId == id) continue;
                            statusEffects.add(id);
                        }
                    }

                    List<AbstractConfigListEntry> collection = new ArrayList<>();
                    for (Identifier id : Registries.STATUS_EFFECT.getIds()) {
                        collection.add(
                                ENTRY_BUILDER.startBooleanToggle(Registries.STATUS_EFFECT.get(id).getName(), !statusEffects.contains(id))
                                        .setDefaultValue(true)
                                        .setSaveConsumer(newValue -> setStatusEffect(id, newValue))
                                        .build()
                        );
                    }

                    return collection;
                },
                field -> field.getType() == Set.class,
                StatusEffects.class
        );
    }

    public void loadConfig() {
        AutoConfig.register(PotionWarningConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(PotionWarningConfig.class).getConfig();
        registerAnnotations();
    }

    public void setStatusEffect(Identifier id, boolean value) {
        if (value) {
            config.disabledStatusEffects.remove(id);
        } else {
            config.disabledStatusEffects.add(id);
        }
    }

    public boolean isDisabled(Identifier id) {
        return config.disabledStatusEffects.contains(id);
    }
}

