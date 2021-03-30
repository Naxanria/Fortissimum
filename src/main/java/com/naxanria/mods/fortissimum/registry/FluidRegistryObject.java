package com.naxanria.mods.fortissimum.registry;

import com.naxanria.mods.fortissimum.Fortissimum;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

/*
  @author: Naxanria
*/
public class FluidRegistryObject<STILL extends Fluid, FLOWING extends Fluid, BLOCK extends FlowingFluidBlock, BUCKET extends BucketItem>
{
  private RegistryObject<STILL> stillRO;
  private RegistryObject<FLOWING> flowingRO;
  private RegistryObject<BLOCK> blockRO;
  private RegistryObject<BUCKET> bucketRO;
  private String name;
  
  public FluidRegistryObject(String name)
  {
    this.name = name;
    stillRO = RegistryObject.of(Fortissimum.modLoc(name), ForgeRegistries.FLUIDS);
    flowingRO = RegistryObject.of(Fortissimum.modLoc(name + "_flowing"), ForgeRegistries.FLUIDS);
    blockRO = RegistryObject.of(Fortissimum.modLoc(name), ForgeRegistries.BLOCKS);
    bucketRO = RegistryObject.of(Fortissimum.modLoc(name + "_bucket"), ForgeRegistries.ITEMS);
  }
  
  public STILL getStill()
  {
    return stillRO.get();
  }
  
  public FLOWING getFlowing()
  {
    return flowingRO.get();
  }
  
  public BLOCK getBlock()
  {
    return blockRO.get();
  }
  
  public BUCKET getBucket()
  {
    return bucketRO.get();
  }
  
  void updateStill(RegistryObject<STILL> stillRO)
  {
    this.stillRO = Objects.requireNonNull(stillRO);
  }
  
  void updateFlowing(RegistryObject<FLOWING> flowingRO)
  {
    this.flowingRO = Objects.requireNonNull(flowingRO);
  }
  
  void updateBlock(RegistryObject<BLOCK> blockRO)
  {
    this.blockRO = Objects.requireNonNull(blockRO);
  }
  
  void updateBucket(RegistryObject<BUCKET> bucketRO)
  {
    this.bucketRO = Objects.requireNonNull(bucketRO);
  }
  
  public String getName()
  {
    return name;
  }
  
  public ResourceLocation getLocation()
  {
    return Fortissimum.modLoc(name);
  }
  
  public STILL getFluid()
  {
    return getStill();
  }
}
