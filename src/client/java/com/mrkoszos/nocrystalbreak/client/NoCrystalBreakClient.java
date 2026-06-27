package com.mrkoszos.nocrystalbreak.client;

import com.mrkoszos.nocrystalbreak.config.NoCrystalBreakConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoCrystalBreakClient implements ClientModInitializer {
	public static final String MOD_ID = "nocrystalbreak";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		// Register the config.
		AutoConfig.register(NoCrystalBreakConfig.class, GsonConfigSerializer::new);

		LOGGER.info("NoCrystalBreak client initialized.");

		// Prevent attacking obsidian while holding an End Crystal.
		AttackBlockCallback.EVENT.register((player, level, hand, pos, direction) -> {
			NoCrystalBreakConfig config = AutoConfig
					.getConfigHolder(NoCrystalBreakConfig.class)
					.getConfig();

			if (!config.enabled) {
				return InteractionResult.PASS;
			}

			BlockState state = level.getBlockState(pos);

			if (state.getBlock() == Blocks.OBSIDIAN && player.getItemInHand(hand).getItem() == Items.END_CRYSTAL) {
				LOGGER.info("");
				return InteractionResult.FAIL;
			}

			return InteractionResult.PASS;
		});
	}
}