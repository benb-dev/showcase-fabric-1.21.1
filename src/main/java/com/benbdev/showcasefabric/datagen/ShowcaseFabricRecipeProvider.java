package com.benbdev.showcasefabric.datagen;

import com.benbdev.showcasefabric.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

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
        createWoodenHoemmerRecipe(recipeExporter);
        createHoemmerRecipe(recipeExporter, Items.COBBLESTONE, ModItems.STONE_AOE_HOE);
        createHoemmerRecipe(recipeExporter, Items.IRON_INGOT, ModItems.IRON_AOE_HOE);
        createHoemmerRecipe(recipeExporter, Items.GOLD_INGOT, ModItems.GOLDEN_AOE_HOE);
        createHoemmerRecipe(recipeExporter, Items.DIAMOND, ModItems.DIAMOND_AOE_HOE);
        createHoemmerRecipe(recipeExporter, Items.NETHERITE_INGOT, ModItems.NETHERITE_AOE_HOE);
    }

    private void createHoemmerRecipe(RecipeExporter exporter, Item material, Item output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output)
                .pattern("MM ")
                .pattern("MS ")
                .pattern("MS ")
                .input('M', material)
                .input('S', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(material),
                        FabricRecipeProvider.conditionsFromItem(material))
                .offerTo(exporter);
    }

    private void createWoodenHoemmerRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.WOODEN_AOE_HOE)
                .pattern("MM ")
                .pattern("MS ")
                .pattern("MS ")
                .input('M', Ingredient.fromTag(ItemTags.PLANKS))
                .input('S', Items.STICK)
                .criterion(hasItem(Items.OAK_PLANKS), conditionsFromItem(Items.OAK_PLANKS))
                .offerTo(exporter);
    }
}
