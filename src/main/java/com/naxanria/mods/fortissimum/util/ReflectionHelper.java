package com.naxanria.mods.fortissimum.util;

import com.naxanria.mods.fortissimum.registry.ModBlocks;
import com.naxanria.mods.fortissimum.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/*
  @author: Naxanria
*/
public class ReflectionHelper
{
  public static List<RegistryObject<Block>> getBlocks()
  {
    Class<ModBlocks> clazz = ModBlocks.class;
    List<RegistryObject<Block>> blocks = new ArrayList<>();
    Field[] declaredFields = clazz.getDeclaredFields();
    for (Field field : declaredFields)
    {
      if (Modifier.isStatic(field.getModifiers()))
      {
        Class<?> type = field.getType();
        
        if (type == RegistryObject.class)
        {
          try
          {
            blocks.add((RegistryObject<Block>) field.get(null));
          }
          catch (IllegalAccessException e)
          {
            e.printStackTrace();
          }
        }
      }
    }
    return blocks;
  }
  
  public static List<RegistryObject<Item>> getItems()
  {
    Class<ModItems> clazz = ModItems.class;
    List<RegistryObject<Item>> items = new ArrayList<>();
    Field[] declaredFields = clazz.getDeclaredFields();
    for (Field field : declaredFields)
    {
      if (Modifier.isStatic(field.getModifiers()))
      {
        Class<?> type = field.getType();
        
        if (type == RegistryObject.class)
        {
          try
          {
            items.add((RegistryObject<Item>) field.get(null));
          }
          catch (IllegalAccessException e)
          {
            e.printStackTrace();
          }
        }
      }
    }
    return items;
  }
}
