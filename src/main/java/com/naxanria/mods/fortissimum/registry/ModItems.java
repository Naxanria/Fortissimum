package com.naxanria.mods.fortissimum.registry;

import com.naxanria.mods.fortissimum.Fortissimum;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

/*
  @author: Naxanria
*/
public class ModItems
{
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Fortissimum.MODID);
  
  public static final RegistryObject<Item> FORTIORIUM = register("fortiorium");
  public static final RegistryObject<Item> FORTINOXIUM = register("fortinoxium_ingot");
  
  private static RegistryObject<Item> register(String name)
  {
    return register(name, null);
  }
  
  private static RegistryObject<Item> register(String name, final Consumer<Item.Properties> propertiesConsumer)
  {
    return ITEMS.register(name, () ->
    {
      Item.Properties properties = properties();
      if (propertiesConsumer != null)
      {
        propertiesConsumer.accept(properties);
      }
      return new Item(properties);
    });
  }
  
  private static RegistryObject<Item> registerFromBlock(RegistryObject<Block> blockObject)
  {
    ResourceLocation registryName = blockObject.getId();
    
    return ITEMS.register(registryName.getPath(), () ->
    {
      Block block = blockObject.get();
      return new BlockItem(block, properties());
    });
  }
  
  static Item.Properties properties()
  {
    return new Item.Properties().tab(Misc.GROUP);
  }
  
  public static void initBlockItems(final RegistryEvent.Register<Item> event)
  {
    ModBlocks.BLOCKS.getEntries().forEach(ModItems::registerFromBlock);
  }
}
