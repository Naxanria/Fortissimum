package com.naxanria.mods.fortissimum.registry;

import com.naxanria.mods.fortissimum.Fortissimum;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/*
  @author: Naxanria
*/
public class Misc
{
  public static final ItemGroup GROUP = new ItemGroup(Fortissimum.MODID)
  {
    @Override
    public ItemStack makeIcon()
    {
      return new ItemStack(ModItems.FORTIORIUM.get());
    }
  };
}
