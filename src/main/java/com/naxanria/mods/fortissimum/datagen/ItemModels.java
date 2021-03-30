package com.naxanria.mods.fortissimum.datagen;

import com.naxanria.mods.fortissimum.Fortissimum;
import com.naxanria.mods.fortissimum.registry.ModFluids;
import com.naxanria.mods.fortissimum.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.DynamicBucketModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

/*
  @author: Naxanria
*/
public class ItemModels extends ItemModelProvider
{
  public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper)
  {
    super(generator, Fortissimum.MODID, existingFileHelper);
  }
  
  @Override
  protected void registerModels()
  {
    blocks();
    items();
  }
  
  private void blocks()
  {
    ModItems.ITEMS.getEntries().stream().filter(ro -> ro.get() instanceof BlockItem).forEach(this::simpleBlock);
  }
  
  private void items()
  {
    //noinspection unchecked
    simpleItem
    (
      ModItems.FORTINOXIUM,
      ModItems.FORTIORIUM
    );
    
    registerBuckets();
  }
  
  protected void simpleItem(RegistryObject<Item>... registryObjects)
  {
    for (RegistryObject<Item> registryObject : registryObjects)
    {
      simpleItem(registryObject);
    }
  }
  
  protected ItemModelBuilder simpleItem(RegistryObject<Item> registryObject)
  {
    String path = registryObject.getId().getPath();
    Fortissimum.LOGGER.info("Creating simple item for " + registryObject.getId());
    return singleTexture(path, mcLoc("item/generated"), "layer0", modLoc("item/" + path));
  }
  
  protected ItemModelBuilder simpleBlock(RegistryObject<Item> registryObject)
  {
    Fortissimum.LOGGER.info("Creating simple item block for " + registryObject.getId());
    String path = registryObject.getId().getPath();
    return getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
  }
  
  private void registerBuckets()
  {
    ModFluids.FLUIDS.getAll().forEach(fro ->
    {
      Fortissimum.LOGGER.info("Creating bucket for {}", fro.getName());
      withExistingParent(fro.getBucket().getRegistryName().getPath(), new ResourceLocation("forge:item/bucket"))
        .customLoader(DynamicBucketModelBuilder::begin)
        .fluid(fro.getFluid());
    });
  }
}
