package com.naxanria.mods.fortissimum.datagen;

import com.naxanria.mods.fortissimum.Fortissimum;
import com.naxanria.mods.fortissimum.registry.ModBlocks;
import com.naxanria.mods.fortissimum.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.*;

/*
  @author: Naxanria
*/
public class Lang extends LanguageProvider
{
  private static final Set<String> keepLowerCase = new HashSet<>(Arrays.asList("a", "the", "an", "of"));
  
  private final Map<RegistryObject<Block>, String> blocks = new HashMap<>();
  private final Map<RegistryObject<Item>, String> items = new HashMap<>();
  
  public Lang(DataGenerator gen)
  {
    super(gen, Fortissimum.MODID, "en_us");
  }
  
  @Override
  protected void addTranslations()
  {
    ModItems.ITEMS.getEntries().forEach(registryObject ->
    {
      if (!(registryObject.get() instanceof BlockItem))
      {
        addItem(registryObject);
      }
    });
    
    ModBlocks.BLOCKS.getEntries().forEach(this::addBlock);
    
    add("itemGroup." + Fortissimum.MODID, "Fortissimum");
    
    generate();
  }
  
  
  public void addBlock(RegistryObject<Block> key, String name)
  {
    Fortissimum.LOGGER.info("Lang key for {}: {}", key.getId(), name);
    blocks.put(key, name);
  }
  
  public void addItem(RegistryObject<Item> key, String name)
  {
    Fortissimum.LOGGER.info("Lang key for {}: {}", key.getId(), name);
    items.put(key, name);
  }
  
  protected void addItem(RegistryObject<Item> registryObject)
  {
    String name = getDefault(registryObject.getId());
    addItem(registryObject, name);
  }
  
  protected void addBlock(RegistryObject<Block> registryObject)
  {
    String name = getDefault(registryObject.getId());
    addBlock(registryObject, name);
  }
  
  private void generate()
  {
    items.forEach(super::addItem);
    blocks.forEach(super::addBlock);
  }
  
  private String getDefault(ResourceLocation location)
  {
    String path = location.getPath();
    
    String[] split = path.split("_");
    
    StringBuilder def = new StringBuilder();
    boolean first = true;
    for (String s : split)
    {
      if (first)
      {
        def.append(capitalizeFirst(s));
        first = false;
        
      }
      else
      {
        def.append(keepLowerCase.contains(s) ? s : capitalizeFirst(s));
      }
      
      def.append(" ");
    }
    
    // remove trailing space
    def.delete(def.length() - 1, def.length());
    return def.toString();
  }
  
  private String capitalizeFirst(String s)
  {
    if (s.length() > 1)
    {
      return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
    
    return s.toUpperCase();
  }
}
