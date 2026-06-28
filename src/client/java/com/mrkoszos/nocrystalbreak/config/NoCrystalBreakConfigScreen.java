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
				.setTitle(Text.literal("Safe Crystals Config"));

		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));

		general.addEntry(
				entryBuilder.startBooleanToggle(
								Text.literal("Enable mod"),
								AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).getConfig().enabled
						)
						.setDefaultValue(true)
						.setTooltip(Text.literal("If enabled, players cannot punch obsidian while holding an End Crystal."))
						.setSaveConsumer(value ->
								AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).getConfig().enabled = value
						)
						.build()
		);

		builder.setSavingRunnable(() ->
				AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).save()
		);

		return builder.build();
	}
}