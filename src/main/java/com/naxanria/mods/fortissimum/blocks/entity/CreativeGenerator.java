package com.naxanria.mods.fortissimum.blocks.entity;

import com.naxanria.mods.fortissimum.Fortissimum;
import com.naxanria.mods.fortissimum.blocks.entity.util.energy.BaseEnergyStorage;
import com.naxanria.mods.fortissimum.registry.ModTiles;

import javax.annotation.Nonnull;

/*
  @author: Naxanria
*/
public class CreativeGenerator extends Generator
{
  private BaseEnergyStorage storage;
  private int tick = 0;
  
  public CreativeGenerator()
  {
    super(ModTiles.CREATIVE_GENERATOR.get());
  }
  
  @Override
  protected int generate()
  {
    return Integer.MAX_VALUE;
  }
  
  @Override
  protected void update()
  {
    if (++tick % 20 == 0)
    {
      Fortissimum.LOGGER.info("T I C K");
    }
  }
  
  @Nonnull
  @Override
  protected BaseEnergyStorage getEnergyStorage()
  {
    if (storage == null)
    {
      storage = new BaseEnergyStorage(Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    return storage;
  }
}
