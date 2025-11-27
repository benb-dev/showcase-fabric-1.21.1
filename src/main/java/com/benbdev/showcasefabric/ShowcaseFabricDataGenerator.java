package com.benbdev.showcasefabric;

import com.benbdev.showcasefabric.datagen.ShowcaseFabricItemTagProvider;
import com.benbdev.showcasefabric.datagen.ShowcaseFabricLootTableProvider;
import com.benbdev.showcasefabric.datagen.ShowcaseFabricModelProvider;
import com.benbdev.showcasefabric.datagen.ShowcaseFabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ShowcaseFabricDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ShowcaseFabricRecipeProvider::new);
        pack.addProvider(ShowcaseFabricModelProvider::new);
        pack.addProvider(ShowcaseFabricLootTableProvider::new);
        pack.addProvider(ShowcaseFabricItemTagProvider::new);
    }
}
