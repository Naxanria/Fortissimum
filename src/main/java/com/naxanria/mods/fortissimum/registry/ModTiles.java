package com.naxanria.mods.fortissimum.registry;

import com.naxanria.mods.fortissimum.Fortissimum;
import com.naxanria.mods.fortissimum.blocks.entity.CreativeGenerator;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.mojang.datafixers.types.Type;

import java.util.function.Supplier;

/*
  @author: Naxanria
*/
public class ModTiles
{
  public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Fortissimum.MODID);
  
  public static final RegistryObject<TileEntityType<CreativeGenerator>> CREATIVE_GENERATOR = register("creative_generator", CreativeGenerator::new, ModBlocks.CREATIVE_GENERATOR);
  
  
  private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> supplier, RegistryObject<Block> block)
  {
    return register(name, supplier, block, null);
  }
  
  private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> supplier, RegistryObject<Block> block, Type<?> dataFixer)
  {
    return TILES.register(name, () -> TileEntityType.Builder.of(supplier, block.get()).build(dataFixer));
  }
}
