package com.mrkoszos.nocrystalbreak.client;

import com.mrkoszos.nocrystalbreak.config.NoCrystalBreakConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

public class NoCrystalBreakClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		AutoConfig.register(NoCrystalBreakConfig.class, GsonConfigSerializer::new);

		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			NoCrystalBreakConfig config = AutoConfig
					.getConfigHolder(NoCrystalBreakConfig.class)
					.getConfig();

			if (!config.enabled) {
				return ActionResult.PASS;
			}

			BlockState state = world.getBlockState(pos);

			if (state.getBlock() == Blocks.OBSIDIAN && player.getStackInHand(hand).getItem() == Items.END_CRYSTAL) {
				return ActionResult.FAIL;
			}

			return ActionResult.PASS;
		});
	}
}