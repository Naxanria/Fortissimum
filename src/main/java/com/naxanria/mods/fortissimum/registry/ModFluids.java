package com.naxanria.mods.fortissimum.registry;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BucketItem;
import net.minecraftforge.fluids.ForgeFlowingFluid;

/*
  @author: Naxanria
*/
public class ModFluids
{
  public static final FluidDeferredRegistry FLUIDS = new FluidDeferredRegistry();
  
  public static final FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> NOXIOUS_ACID = FLUIDS.register("noxious_acid",
    attributes ->  attributes.color(0xFF7F3CDF));
}
