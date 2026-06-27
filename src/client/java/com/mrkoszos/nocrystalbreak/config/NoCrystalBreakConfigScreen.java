package com.mrkoszos.nocrystalbreak.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class NoCrystalBreakConfigScreen {

        public static Screen create(Screen parent) {
                ConfigBuilder builder = ConfigBuilder.create()
                                .setParentScreen(parent)
                                .setTitle(Component.literal("Safe Crystals Config"));

                ConfigEntryBuilder entryBuilder = builder.entryBuilder();

                ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));

                general.addEntry(
                                entryBuilder.startBooleanToggle(
                                                Component.literal("Enable mod"),
                                                AutoConfig.getConfigHolder(NoCrystalBreakConfig.class)
                                                                .getConfig().enabled)
                                                .setDefaultValue(true)
                                                .setTooltip(Component.literal(
                                                                "If enabled, players cannot punch obsidian while holding an End Crystal."))
                                                .setSaveConsumer(value -> AutoConfig
                                                                .getConfigHolder(NoCrystalBreakConfig.class)
                                                                .getConfig().enabled = value)
                                                .build());

                builder.setSavingRunnable(() -> AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).save());

                return builder.build();
        }
}