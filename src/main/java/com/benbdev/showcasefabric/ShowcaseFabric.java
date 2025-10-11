package com.benbdev.showcasefabric;

import com.benbdev.showcasefabric.block.ModBlocks;
import com.benbdev.showcasefabric.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowcaseFabric implements ModInitializer {
	public static final String MOD_ID = "showcasefabric";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
	}
}