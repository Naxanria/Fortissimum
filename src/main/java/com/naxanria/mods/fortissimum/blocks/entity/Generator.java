package com.naxanria.mods.fortissimum.blocks.entity;

import com.naxanria.mods.fortissimum.blocks.entity.util.energy.BaseEnergyStorage;
import com.naxanria.mods.fortissimum.util.sidedstate.BooleanSidedState;
import com.naxanria.mods.fortissimum.util.sidedstate.SidedStateContainer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*
  @author: Naxanria
*/
public abstract class Generator extends TileEntity implements ITickableTileEntity
{
  protected SidedStateContainer<BooleanSidedState> outputSides = SidedStateContainer.full(BooleanSidedState.TRUE);
  
  protected LazyOptional<BaseEnergyStorage> energyStorage = LazyOptional.of(this::getEnergyStorage);
  
  public Generator(TileEntityType<?> type)
  {
    super(type);
  }
  
  @Override
  public final void tick()
  {
    if (!level.isClientSide())
    {
      int gen = generate();
      energyStorage.ifPresent(storage ->
      {
        storage.receiveInternal(gen, false);
        if (storage.getEnergyStored() > 0)
        {
          storage.extractInternal(sharePower(), false);
        }
      });
      
      update();
    }
  }
  
  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
  {
    if (cap == CapabilityEnergy.ENERGY && outputSides.isValidSide(side) && outputSides.getState(side).getState())
    {
      return energyStorage.cast();
    }
    
    return super.getCapability(cap, side);
  }
  
  protected void update()
  { }
  
  protected abstract int generate();
  
  @Nonnull
  protected abstract BaseEnergyStorage getEnergyStorage();
  
  protected int sharePower()
  {
    return 1;
  }
}
