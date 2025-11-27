package com.benbdev.showcasefabric.datagen;

import com.benbdev.showcasefabric.item.FoodStuffs;
import com.benbdev.showcasefabric.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ShowcaseFabricRecipeProvider extends FabricRecipeProvider {
    TagKey<Item> FOOD = TagKey.of(
            RegistryKeys.ITEM,
            Identifier.of("minecraft", "food")
    );

    public ShowcaseFabricRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FRESH_WATER_ITEM, 8)
                .input(Items.WATER_BUCKET)
                .criterion(FabricRecipeProvider.hasItem(Items.WATER_BUCKET), FabricRecipeProvider.conditionsFromItem(Items.WATER_BUCKET))
                .offerTo(recipeExporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FRESH_MILK_ITEM, 8)
                .input(Items.MILK_BUCKET)
                .criterion(FabricRecipeProvider.hasItem(Items.MILK_BUCKET), FabricRecipeProvider.conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(recipeExporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.YOGURT_ITEM, 1)
                .input(ModItems.FRESH_MILK_ITEM)
                .criterion(FabricRecipeProvider.hasItem(ModItems.FRESH_MILK_ITEM), FabricRecipeProvider.conditionsFromItem(ModItems.FRESH_MILK_ITEM))
                .offerTo(recipeExporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FERTILIZER, 3)
                .input(Items.BONE_MEAL)
                .input(Ingredient.fromTag(FOOD))
                .criterion(FabricRecipeProvider.hasItem(Items.BONE_MEAL), FabricRecipeProvider.conditionsFromItem(Items.BONE_MEAL))
                .offerTo(recipeExporter);

        createWoodenHoemmerRecipe(recipeExporter);
        createHoemmerRecipe(recipeExporter, Items.COBBLESTONE, ModItems.STONE_AOE_HOE);
        createHoemmerRecipe(recipeExporter, Items.IRON_INGOT, ModItems.IRON_AOE_HOE);
        createHoemmerRecipe(recipeExporter, Items.GOLD_INGOT, ModItems.GOLDEN_AOE_HOE);
        createHoemmerRecipe(recipeExporter, Items.DIAMOND, ModItems.DIAMOND_AOE_HOE);
        createHoemmerRecipe(recipeExporter, Items.NETHERITE_INGOT, ModItems.NETHERITE_AOE_HOE);
        offerSmelting(
                recipeExporter,
                List.of(ModItems.FRESH_WATER_ITEM),
                RecipeCategory.FOOD,
                ModItems.SALT_ITEM,
                0.35f,
                200,
                "salt_item"
        );
        generateSeedsRecipes(recipeExporter);

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

    private void createSeedRecipe(RecipeExporter exporter, Item cropFood, Item seedOutput) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, seedOutput)
                .input(cropFood)
                .criterion(FabricRecipeProvider.hasItem(cropFood),
                        FabricRecipeProvider.conditionsFromItem(cropFood))
                .offerTo(exporter);
    }

    private void generateSeedsRecipes(RecipeExporter exporter) {
        ModItems.SEED_MAP.forEach((key, value) -> {
            createSeedRecipe(exporter, key, value);
        });
    }
}
