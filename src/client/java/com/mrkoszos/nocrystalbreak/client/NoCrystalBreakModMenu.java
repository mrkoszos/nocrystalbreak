package com.mrkoszos.nocrystalbreak.client;

import com.mrkoszos.nocrystalbreak.config.NoCrystalBreakConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class NoCrystalBreakModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.literal("NoCrystalBreak Config"));

            ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));

            BooleanToggleBuilder enabledEntry = builder.entryBuilder()
                    .startBooleanToggle(Text.literal("Enable crystal-block protection"), getConfig().enabled)
                    .setDefaultValue(getConfig().enabled)
                    .setSaveConsumer(value -> getConfig().enabled = value);

            general.addEntry(enabledEntry.build());

            builder.setSavingRunnable(this::saveConfig);

            return builder.build();
        };
    }

    private NoCrystalBreakConfig getConfig() {
        return AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).getConfig();
    }

    private void saveConfig() {
        AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).save();
    }
}
