package com.naxanria.mods.fortissimum.datagen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.naxanria.mods.fortissimum.registry.ModBlocks;
import com.naxanria.mods.fortissimum.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ForgeLootTableProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/*
  @author: Naxanria
*/
public class LootTables extends ForgeLootTableProvider
{
  public LootTables(DataGenerator gen)
  {
    super(gen);
  }
  
  @Override
  protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables()
  {
    return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK));
  }
  
  @Override
  protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker)
  {
    map.forEach((location, lootTable) -> LootTableManager.validate(validationtracker, location, lootTable));
  }
  
  private static class Blocks extends BlockLootTables
  {
    @Override
    protected void addTables()
    {
      //noinspection unchecked
      dropSelf
      (
        ModBlocks.NOXIUM_STONE_ORE,
        ModBlocks.NOXIUM_ENDSTONE_ORE,
        
        ModBlocks.FORTINOXIUM_BLOCK,
        
        ModBlocks.CREATIVE_GENERATOR
      );
      
      add(ModBlocks.FORTIORIUM_STONE_ORE.get(), block -> createOreDrop(block, ModItems.FORTIORIUM.get()));
      add(ModBlocks.FORTIORIUM_ENDSTONE_ORE.get(), block -> createOreDrop(block, ModItems.FORTIORIUM.get()));
    }
    
    private void dropSelf(RegistryObject<Block>... registryObjects)
    {
      for (RegistryObject<Block> registryObject : registryObjects)
      {
        dropSelf(registryObject.get());
      }
    }
  
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
      final Set<RegistryObject<Block>> ignore = ImmutableSet.of();
      
      return ModBlocks.BLOCKS.getEntries().stream().filter(ro -> !ignore.contains(ro)).map(RegistryObject::get).collect(Collectors.toList());
    }
  }
}
