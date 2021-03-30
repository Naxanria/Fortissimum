package com.naxanria.mods.fortissimum.registry;

import com.naxanria.mods.fortissimum.Fortissimum;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/*
  @author: Naxanria
*/
public class FluidDeferredRegistry
{
  private static final ResourceLocation WATER_OVERLAY = new ResourceLocation("minecraft:block/water_overlay");
  
  private static final IDispenseItemBehavior BUCKET_DISPENSE_BEHAVIOUR = new DefaultDispenseItemBehavior()
  {
    @Override
    public ItemStack execute(IBlockSource source, ItemStack stack)
    {
      ServerWorld level = source.getLevel();
      BucketItem bucket = (BucketItem) stack.getItem();
    
      BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
      if (bucket.emptyBucket(null, level, pos, null))
      {
        bucket.checkExtraContent(level, stack, pos);
        return bucket.getContainerItem(stack);
      }
      return super.dispense(source, stack);
    }
  };
  
  private final List<FluidRegistryObject<?, ?, ?, ?>> allFluids = new ArrayList<>();
  
  private final DeferredRegister<Fluid> fluidRegister = DeferredRegister.create(ForgeRegistries.FLUIDS, Fortissimum.MODID);
  private final DeferredRegister<Block> blockRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, Fortissimum.MODID);
  private final DeferredRegister<Item> itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, Fortissimum.MODID);

  public void register(IEventBus eventBus)
  {
    fluidRegister.register(eventBus);
    blockRegister.register(eventBus);
    itemRegister.register(eventBus);
  }
  
  public FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> register(String name, UnaryOperator<FluidAttributes.Builder> attributes)
  {
    return register(name, attributes.apply(FluidAttributes.builder(new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow"))));
  }
  
  public FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> register(String name, FluidAttributes.Builder builder)
  {
    String flowingName = name + "_flowing";
    String bucketName = name + "_bucket";
    builder.overlay(WATER_OVERLAY);
  
    FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> fluidRO = new FluidRegistryObject<>(name);
  
    ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(fluidRO::getStill, fluidRO::getFlowing, builder)
      .bucket(fluidRO::getBucket)
      .block(fluidRO::getBlock);
    
    fluidRO.updateStill(fluidRegister.register(name, () -> new ForgeFlowingFluid.Source(properties)));
    fluidRO.updateFlowing(fluidRegister.register(flowingName, () -> new ForgeFlowingFluid.Flowing(properties)));
    fluidRO.updateBlock(blockRegister.register(name, () -> new FlowingFluidBlock(fluidRO::getStill,
      AbstractBlock.Properties.of(Material.WATER)
        .noCollission()
        .strength(100)
        .noDrops()
    )));
    fluidRO.updateBucket(itemRegister.register(bucketName, () -> new BucketItem(fluidRO::getStill,
      ModItems.properties().stacksTo(1).craftRemainder(Items.BUCKET))
    ));
    
    allFluids.add(fluidRO);
    
    return fluidRO;
  }
  
  public void registerDispenserBehaviour()
  {
    allFluids.forEach(fro -> DispenserBlock.registerBehavior(fro.getBucket(), BUCKET_DISPENSE_BEHAVIOUR));
  }
  
  public List<FluidRegistryObject<?, ?, ?, ?>> getAll()
  {
    return allFluids;
  }
}
