package com.benbdev.showcasefabric.datagen;

import com.benbdev.showcasefabric.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ShowcaseFabricRecipeProvider extends FabricRecipeProvider {
    public ShowcaseFabricRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.YOGURT_ITEM, 4)
                .input(Items.MILK_BUCKET)
                .criterion(FabricRecipeProvider.hasItem(Items.MILK_BUCKET), FabricRecipeProvider.conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(recipeExporter);
    }
}
