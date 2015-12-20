package mods.natura.plugins;

import mods.natura.plugins.IPluginBase;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import mods.natura.Natura;

import mods.natura.plugins.*;

public class PluginManager
{
    static final IPluginBase plugins[] = {new Waila(), new Buildcraft(), new Thaumcraft()};

    public static void preInit (FMLPreInitializationEvent evt)
    {
    	for (int i = 0; i < plugins.length; i++)
    		if (plugins[i] != null)
    		{
    			String name = plugins[i].name ();
    			Natura.logger.debug("[Plugin] preInit of " + name + ".");
    			if (!plugins[i].loadable ())
    				plugins[i] = null;
    			if (plugins[i] != null)
    				plugins[i].preInit (evt);
    			else
    				Natura.logger.debug("[Plugin] " + name + " was disabled.");
    		}
    }

    public static void init (FMLInitializationEvent evt)
    {
    	for (int i = 0; i < plugins.length; i++)
    		if (plugins[i] != null)
    		{
    			Natura.logger.debug("[Plugin] init of " + plugins[i].name () + ".");
    			plugins[i].init (evt);
    		}
    }

    public static void postInit (FMLPostInitializationEvent evt)
    {
    	for (int i = 0; i < plugins.length; i++)
    		if (plugins[i] != null)
    		{
    			Natura.logger.debug("[Plugin] postInit of " + plugins[i].name () + ".");
    			plugins[i].postInit (evt);
    		}
    }

    public static void config (Configuration cfg)
    {
    	for (int i = 0; i < plugins.length; i++)
    		if (!cfg.get("Plugins", "Enable " + plugins[i].name (), true).getBoolean(true))
    			plugins[i] = null;
    }
}
