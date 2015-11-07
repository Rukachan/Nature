package mods.natura.plugins;

import mods.natura.plugins.IPluginBase;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Optional;

import net.minecraft.item.ItemStack;

import codechicken.nei.api.API;
import codechicken.nei.recipe.DefaultOverlayHandler;
import cpw.mods.fml.relauncher.Side;
import mods.natura.common.NContent;
import mods.natura.gui.WorkbenchGui;

import cpw.mods.fml.common.Loader;


public class NEI implements IPluginBase
{
    public static final String modId = "NotEnoughItems";

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
    	/* do we really need both checks? */
    	if (!FMLCommonHandler.instance().getSide().isServer() && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
    	{
    		API.registerGuiOverlay(WorkbenchGui.class, "crafting");
    		API.registerGuiOverlayHandler(WorkbenchGui.class, new DefaultOverlayHandler(), "crafting");

    		API.hideItem(new ItemStack(NContent.crops));
    		API.hideItem(new ItemStack(NContent.redwoodDoor));
    		API.hideItem(new ItemStack(NContent.eucalyptusDoor));
    		API.hideItem(new ItemStack(NContent.hopseedDoor));
    		API.hideItem(new ItemStack(NContent.sakuraDoor));
    		API.hideItem(new ItemStack(NContent.ghostDoor));
    		API.hideItem(new ItemStack(NContent.bloodDoor));
    		API.hideItem(new ItemStack(NContent.redwoodBarkDoor));
    	}
    }

    @Override
    @Optional.Method (modid = modId)
    public void postInit (FMLPostInitializationEvent evt)
    {
    }
}
