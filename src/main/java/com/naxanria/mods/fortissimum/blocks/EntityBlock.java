package com.naxanria.mods.fortissimum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

/*
  @author: Naxanria
*/
public class EntityBlock<T extends TileEntity> extends Block
{
  private final Supplier<T> blockEntitySupplier;
  
  public EntityBlock(Properties properties, Supplier<T> blockEntitySupplier)
  {
    super(properties);
    this.blockEntitySupplier = blockEntitySupplier;
  }
  
  @Override
  public boolean hasTileEntity(BlockState state)
  {
    return true;
  }
  
  @Nullable
  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world)
  {
    return blockEntitySupplier.get();
  }
  
  public Optional<T> getBlockEntity(World world, BlockPos pos)
  {
    TileEntity te = world.getBlockEntity(pos);
    return Optional.ofNullable((T) te);
  }
}
