package com.mrkoszos.nocrystalbreak;

import net.fabricmc.api.ModInitializer;
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

public class NoCrystalBreak implements ModInitializer {
	public static final String MOD_ID = "nocrystalbreak";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("NoCrystalBreak mod initialized!");

		AttackBlockCallback.EVENT.register((PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) -> {
			BlockState state = world.getBlockState(pos);

			// Check if the block is obsidian and the player is holding an Ender Crystal
			if (state.getBlock() == Blocks.OBSIDIAN && player.getStackInHand(hand).getItem() == Items.END_CRYSTAL) {
				LOGGER.info("Prevented breaking obsidian while holding an Ender Crystal.");
				return ActionResult.FAIL; // Prevents the block breaking action
			}

			return ActionResult.PASS; // Allows other interactions
		});
	}
}