package com.naxanria.mods.fortissimum.registry;

import com.naxanria.mods.fortissimum.Fortissimum;
import com.naxanria.mods.fortissimum.blocks.EntityBlock;
import com.naxanria.mods.fortissimum.blocks.entity.CreativeGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*
  @author: Naxanria
*/
public class ModBlocks
{
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Fortissimum.MODID);
  
  public static final RegistryObject<Block> FORTIORIUM_STONE_ORE = register("fortiorium_stone_ore", Material.STONE, ToolType.PICKAXE, ItemTier.NETHERITE.getLevel(),30);
  public static final RegistryObject<Block> FORTIORIUM_ENDSTONE_ORE = register("fortiorium_endstone_ore", Material.STONE, MaterialColor.SAND, ToolType.PICKAXE, ItemTier.NETHERITE.getLevel(),30);
  public static final RegistryObject<Block> NOXIUM_STONE_ORE = register("noxium_stone_ore", Material.STONE,ToolType.PICKAXE, ItemTier.NETHERITE.getLevel(),30);
  public static final RegistryObject<Block> NOXIUM_ENDSTONE_ORE = register("noxium_endstone_ore", Material.STONE, MaterialColor.SAND, ToolType.PICKAXE, ItemTier.NETHERITE.getLevel(),30);
  
  public static final RegistryObject<Block> FORTINOXIUM_BLOCK = register("fortinoxium_block", Material.METAL, MaterialColor.COLOR_MAGENTA, ToolType.PICKAXE, ItemTier.NETHERITE.getLevel(), 30);
  
  public static final RegistryObject<Block> CREATIVE_GENERATOR = BLOCKS.register("creative_generator", () -> new EntityBlock<>
  (
    Block.Properties.of(Material.METAL).strength(25, 25).harvestTool(ToolType.PICKAXE).harvestLevel(ItemTier.DIAMOND.getLevel()),
    CreativeGenerator::new
  ));
  
  private static RegistryObject<Block> register(String name, Material material, ToolType type, int level, float hardnessAndResistance)
  {
    return register(name, material, type, level, hardnessAndResistance, hardnessAndResistance);
  }
  
  private static RegistryObject<Block> register(String name, Material material, ToolType type, int level, float hardness, float resistance)
  {
    return register(name, material, material.getColor(), type, level, hardness, resistance);
  }
  
  private static RegistryObject<Block> register(String name, Material material, MaterialColor color, ToolType type, int level, float hardnessAndResistance)
  {
    return register(name, material, color, type, level, hardnessAndResistance, hardnessAndResistance);
  }
  
  private static RegistryObject<Block> register(String name, Material material, MaterialColor color, ToolType type, int level, float hardness, float resistance)
  {
    return BLOCKS.register(name, () -> new Block(Block.Properties.of(material, color).strength(hardness, resistance).harvestTool(type).harvestLevel(level)));
  }
}
