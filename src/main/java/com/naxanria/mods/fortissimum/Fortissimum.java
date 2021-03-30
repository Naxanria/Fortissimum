package com.naxanria.mods.fortissimum;

/*
  @author: Naxanria
*/

import com.naxanria.mods.fortissimum.datagen.Datagen;
import com.naxanria.mods.fortissimum.registry.ModBlocks;
import com.naxanria.mods.fortissimum.registry.ModFluids;
import com.naxanria.mods.fortissimum.registry.ModItems;
import com.naxanria.mods.fortissimum.registry.ModTiles;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Fortissimum.MODID)
public class Fortissimum
{
  public static final String MODID = "fortissimum";
  public static final Logger LOGGER = LogManager.getLogger(MODID);
  
  public Fortissimum()
  {
    IEventBus fmlModBus = FMLJavaModLoadingContext.get().getModEventBus();
    fmlModBus.addListener(this::commonSetup);
    
    ModBlocks.BLOCKS.register(fmlModBus);
    fmlModBus.addGenericListener(Item.class, ModItems::initBlockItems);
    ModItems.ITEMS.register(fmlModBus);
    ModTiles.TILES.register(fmlModBus);
    ModFluids.FLUIDS.register(fmlModBus);
  
    fmlModBus.addListener(Datagen::datagen);
  }
  
  private void commonSetup(final FMLCommonSetupEvent event)
  {
    event.enqueueWork(ModFluids.FLUIDS::registerDispenserBehaviour);
  }
  
  public static ResourceLocation modLoc(String path)
  {
    return new ResourceLocation(MODID, path);
  }
  
}
