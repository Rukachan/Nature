package mods.natura.plugins;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import mods.natura.plugins.IPluginBase;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

import mods.natura.common.NContent;
import net.minecraft.block.Block;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;

import cpw.mods.fml.common.Optional;

public class FMP implements IPluginBase
{
    public static final String modId = "ForgeMultipart";

    @Override
    public String name ()
    {
      return modId;
    }

    @Override
	public boolean loadable ()
    {
    	return Loader.isModLoaded(modId);
    }

    @Override
    @Optional.Method (modid = modId)
    public void preInit (FMLPreInitializationEvent evt)
    {
    }

    @Override
    @Optional.Method (modid = modId)
    public void init (FMLInitializationEvent evt)
    {
	registerBlock(NContent.bloodwood);
	registerBlock(NContent.willow);
	registerBlock(NContent.planks, 0, 12);
	registerBlock(NContent.tree, 0, 3);
	registerBlock(NContent.rareTree, 0, 3);
	registerBlock(NContent.rareLeaves, 0, 3);
	registerBlock(NContent.darkLeaves, 0, 3);
	registerBlock(NContent.redwood, 0, 2);
	registerBlock(NContent.floraLeaves, 0, 2);
	registerBlock(NContent.floraLeavesNoColor, 0, 2);
    }

    @Override
    @Optional.Method (modid = modId)
    public void postInit (FMLPostInitializationEvent evt)
    {
    }

    //For blocks with metadata values only
    @Optional.Method (modid = modId)
    public static void registerBlock (Block block, int metastart, int metaend)
    {
        for (int meta = metastart; meta <= metaend; meta++)
        {
            String identifier = new String(block.getUnlocalizedName());
            MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, meta), identifier + meta);
        }
    }

    //For blocks without metadata values only.
    @Optional.Method (modid = modId)
    public static void registerBlock (Block block)
    {
        BlockMicroMaterial.createAndRegister(block, 0);
    }

    //For blocks with metadata values and special MicroMaterial only
    @Optional.Method (modid = modId)
    public static void registerBlock (Block block, int metastart, int metaend, MicroMaterialRegistry.IMicroMaterial material)
    {
        for (int meta = metastart; meta <= metaend; meta++)
        {
            String identifier = new String(block.getUnlocalizedName());
            MicroMaterialRegistry.registerMaterial(material, identifier + meta);
        }
    }

    //For blocks without metadata values and special MicroMaterial only.
    @Optional.Method (modid = modId)
    public static void registerBlock (Block block, MicroMaterialRegistry.IMicroMaterial material)
    {
        MicroMaterialRegistry.registerMaterial(material, new String(block.getUnlocalizedName()));
    }
}
