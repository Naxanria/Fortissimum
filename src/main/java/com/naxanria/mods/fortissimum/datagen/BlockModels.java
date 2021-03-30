package com.naxanria.mods.fortissimum.datagen;

import com.naxanria.mods.fortissimum.Fortissimum;
import com.naxanria.mods.fortissimum.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

/*
  @author: Naxanria
*/
public class BlockModels extends BlockModelProvider
{
  public BlockModels(DataGenerator generator, ExistingFileHelper existingFileHelper)
  {
    super(generator, Fortissimum.MODID, existingFileHelper);
  }
  
  @Override
  protected void registerModels()
  {
    simpleBlock
    (
      ModBlocks.NOXIUM_STONE_ORE,
      ModBlocks.NOXIUM_ENDSTONE_ORE,
      ModBlocks.FORTIORIUM_STONE_ORE,
      ModBlocks.FORTIORIUM_ENDSTONE_ORE,
      
      ModBlocks.FORTINOXIUM_BLOCK
    );
  }
  
  private void simpleBlock(RegistryObject<Block>... registryObjects)
  {
    for (RegistryObject<Block> registryObject : registryObjects)
    {
      simpleBlock(registryObject);
    }
  }
  
  private BlockModelBuilder simpleBlock(RegistryObject<Block> registryObject)
  {
    Fortissimum.LOGGER.info("Creating block model for {}", registryObject.getId());
    String path = registryObject.getId().getPath();
    return singleTexture(path, mcLoc("block/cube_all"), "all", modLoc("block/" + path));
  }
}
