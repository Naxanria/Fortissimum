package com.naxanria.mods.fortissimum.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

/*
  @author: Naxanria
*/
public class Datagen
{
  public static Gson GSON;
  
  public static void datagen(final GatherDataEvent event)
  {
    GSON = new GsonBuilder()
      .setPrettyPrinting()
      .create();
  
    DataGenerator generator = event.getGenerator();
    ExistingFileHelper fileHelper = event.getExistingFileHelper();
    // todo: generate client/server sided stuff based on flags
    
    generator.addProvider(new BlockModels(generator, fileHelper));
    generator.addProvider(new BlockStates(generator, fileHelper));
    generator.addProvider(new ItemModels(generator, fileHelper));
    generator.addProvider(new LootTables(generator));
    generator.addProvider(new Lang(generator));
    generator.addProvider(new Recipes(generator));
  }
}
