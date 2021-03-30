package com.naxanria.mods.fortissimum.datagen;


import com.naxanria.mods.fortissimum.Fortissimum;
import com.naxanria.mods.fortissimum.registry.ModBlocks;
import com.naxanria.mods.fortissimum.registry.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

/*
  @author: Naxanria
*/
public class BlockStates extends BlockStateProvider
{
  public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper)
  {
    super(gen, Fortissimum.MODID, exFileHelper);
  }
  
  @Override
  protected void registerStatesAndModels()
  {
    simpleBlock
    (
      ModBlocks.FORTIORIUM_STONE_ORE,
      ModBlocks.FORTIORIUM_ENDSTONE_ORE,
      ModBlocks.NOXIUM_STONE_ORE,
      ModBlocks.NOXIUM_ENDSTONE_ORE,
      
      ModBlocks.FORTINOXIUM_BLOCK
    );
    registerFluidBlockStates();
  }
  
  private void simpleBlock(RegistryObject<Block>... registryObjects)
  {
    for (RegistryObject<Block> registryObject : registryObjects)
    {
      simpleBlock(registryObject);
    }
  }
  
  private void simpleBlock(RegistryObject<Block> registryObject)
  {
    Fortissimum.LOGGER.info("Creating simple block for {}", registryObject.getId());
    ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + registryObject.getId().getPath()));
    simpleBlock(registryObject.get(), model);
  }
  
  private String getBlockName(Block block)
  {
    return block.getRegistryName().getPath();
  }
  
  private void registerFluidBlockStates()
  {
    ModFluids.FLUIDS.getAll().forEach(fro ->
    {
      Fortissimum.LOGGER.info("Creating fluid state for {}", fro.getStill().getRegistryName());
      simpleBlock(fro.getBlock(),
        models().getBuilder(getBlockName(fro.getBlock()))
          .texture("particle", fro.getStill().getAttributes().getStillTexture())
      );
    });
  }
}
