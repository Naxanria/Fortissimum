package com.naxanria.mods.fortissimum.datagen;

import com.naxanria.mods.fortissimum.registry.ModBlocks;
import com.naxanria.mods.fortissimum.registry.ModItems;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;

/*
  @author: Naxanria
*/
public class Recipes extends RecipeProvider
{
  private Consumer<IFinishedRecipe> consumer;
  
  public Recipes(DataGenerator generator)
  {
    super(generator);
  }
  
  @Override
  protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
  {
    this.consumer = consumer;
    
   blockAndBack(ModBlocks.FORTINOXIUM_BLOCK.get(), ModItems.FORTINOXIUM.get(), true);
  }
  
  protected void fromBlock(IItemProvider output, IItemProvider input, int count)
  {
    ShapelessRecipeBuilder.shapeless(output, count)
      .requires(input)
      .unlockedBy("has_" + output.asItem().getRegistryName().getPath(), has(output))
      .save(consumer, output.asItem().getRegistryName().toString() + "_from_" + input.asItem().getRegistryName().getPath());
  }
  
  protected void toBlock(IItemProvider output, IItemProvider input, boolean full)
  {
    ShapedRecipeBuilder.shaped(output)
      .pattern(full ? "iii" : "ii ")
      .pattern(full ? "iii" : "ii ")
      .pattern(full ? "iii" : "   ")
      .define('i', input)
      .unlockedBy("has_" + output.asItem().getRegistryName().getPath(), has(output))
      .save(consumer, output.asItem().getRegistryName().toString() + "_full_block");
  }
  
  protected void toBlock(IItemProvider output, ITag<Item> input, boolean full)
  {
    ShapedRecipeBuilder.shaped(output)
      .pattern(full ? "iii" : "ii ")
      .pattern(full ? "iii" : "ii ")
      .pattern(full ? "iii" : "   ")
      .define('i', input)
      .save(consumer, output.asItem().getRegistryName().toString() + "_full_block");
  }
  
  protected void blockAndBack(IItemProvider block, IItemProvider input, boolean full)
  {
    toBlock(block, input, full);
    fromBlock(input, block, full ? 9 : 4);
  }
}
