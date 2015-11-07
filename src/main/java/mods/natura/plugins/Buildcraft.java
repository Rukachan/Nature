package mods.natura.plugins;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import mods.natura.plugins.IPluginBase;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

import mods.natura.common.NContent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInterModComms;


public class Buildcraft implements IPluginBase
{
    public static final String modId = "BuildCraft|Transport";

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
        for (int i = 0; i < 4; i++)
        {
            addFacade(NContent.berryBush, i);
            addFacade(NContent.netherBerryBush, i);
        }

        addFacade(NContent.saguaro, 0);
    }

    @Override
    @Optional.Method (modid = modId)
    public void postInit (FMLPostInitializationEvent evt)
    {
    }

    @Optional.Method (modid = modId)
    private void addFacade (Block b, int meta)
    {
        FMLInterModComms.sendMessage(modId, "add-facade", new ItemStack(b, 1, meta));
    }
}
