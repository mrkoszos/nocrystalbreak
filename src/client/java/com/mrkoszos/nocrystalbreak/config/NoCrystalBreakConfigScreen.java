package com.mrkoszos.nocrystalbreak.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class NoCrystalBreakConfigScreen {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("SafeCrystals Config"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Text.of("General"));

        general.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.of("Enable mod"),
                                AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).getConfig().enabled
                        )
                        .setTooltip(Text.of("If enabled, players cannot punch obsidian."))
                        .setSaveConsumer(newValue ->
                                AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).getConfig().enabled = newValue
                        )
                        .build()
        );

        builder.setSavingRunnable(() ->
                AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).save()
        );

        return builder.build();
    }
}
