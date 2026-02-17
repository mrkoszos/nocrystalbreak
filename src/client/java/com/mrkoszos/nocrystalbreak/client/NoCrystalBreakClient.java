package com.mrkoszos.nocrystalbreak.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mrkoszos.nocrystalbreak.config.NoCrystalBreakConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class NoCrystalBreakClient implements ClientModInitializer {
	public static final String MOD_ID = "NoCrystalBreakClient";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		// Config register
		AutoConfig.register(NoCrystalBreakConfig.class, GsonConfigSerializer::new);

		LOGGER.info("NoCrystalBreakClient mod initialized!");

		// Callback register
		AttackBlockCallback.EVENT.register((PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) -> {
			// Load config
			NoCrystalBreakConfig config = AutoConfig.getConfigHolder(NoCrystalBreakConfig.class).getConfig();

			// If disabled allow block interaction
			if (!config.enabled) {
				return ActionResult.PASS;
			}

			BlockState state = world.getBlockState(pos);

			// Deny block breaking
			if (state.getBlock() == Blocks.OBSIDIAN && player.getStackInHand(hand).getItem() == Items.END_CRYSTAL) {
				LOGGER.info("Prevented breaking obsidian while holding an Ender Crystal (client).");
				return ActionResult.FAIL;
			}

			return ActionResult.PASS;
		});
	}
}
