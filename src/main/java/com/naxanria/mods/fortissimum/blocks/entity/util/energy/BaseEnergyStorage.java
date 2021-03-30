package com.naxanria.mods.fortissimum.blocks.entity.util.energy;

import net.minecraftforge.energy.IEnergyStorage;

/*
  @author: Naxanria
*/
public class BaseEnergyStorage implements IEnergyStorage
{
  protected int storage;
  protected int maxStorage;
  protected int maxExtract;
  protected int maxReceive;
  
  public BaseEnergyStorage(int maxStorage)
  {
    this.maxStorage = maxStorage;
  }
  
  public BaseEnergyStorage(int maxStorage, int maxExtract)
  {
    this.maxStorage = maxStorage;
    this.maxExtract = maxExtract;
  }
  
  public BaseEnergyStorage(int storage, int maxStorage, int maxExtract, int maxReceive)
  {
    this.storage = storage;
    this.maxStorage = maxStorage;
    this.maxExtract = maxExtract;
    this.maxReceive = maxReceive;
  }
  
  public int receiveInternal(int maxReceive, boolean simulate)
  {
    int received = Math.min(maxReceive, missing());
    if (!simulate)
    {
      storage += received;
    }
    
    return received;
  }
  
  @Override
  public int receiveEnergy(int maxReceive, boolean simulate)
  {
    if (!canReceive())
    {
      return 0;
    }
    
    maxReceive = Math.min(maxReceive, this.maxReceive);
    
    return receiveInternal(maxReceive, simulate);
  }
  
  public int extractInternal(int maxExtract, boolean simulate)
  {
    int extracted = Math.min(maxExtract, storage);
    
    if (!simulate)
    {
      storage -= extracted;
    }
    
    return extracted;
  }
  
  @Override
  public int extractEnergy(int maxExtract, boolean simulate)
  {
    if (!canExtract())
    {
      return 0;
    }
    
    maxExtract = Math.min(maxExtract, this.maxReceive);
    
    return extractInternal(maxExtract, simulate);
  }
  
  @Override
  public int getEnergyStored()
  {
    return storage;
  }
  
  @Override
  public int getMaxEnergyStored()
  {
    return maxStorage;
  }
  
  public int getMaxExtract()
  {
    return maxExtract;
  }
  
  public int getMaxReceive()
  {
    return maxReceive;
  }
  
  @Override
  public boolean canExtract()
  {
    //todo: add logic
    return true;
  }
  
  @Override
  public boolean canReceive()
  {
    //todo: add logic
    return true;
  }
  
  public int missing()
  {
    return maxStorage - storage;
  }
  
  public float percentage()
  {
    return storage == 0 || maxStorage == 0 ? 0 : storage / (float) maxStorage;
  }
}
