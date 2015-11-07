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
    static final IPluginBase plugins[] = {new NEI(), new Waila(), new FMP(), new TE4(), new Buildcraft(), new Thaumcraft()};
    static boolean plugins_enabled[]   = {true,      true,        true,      true,      true,             true}; /* ffs */

    public static void preInit (FMLPreInitializationEvent evt)
    {
      for (int i = 0; i < plugins.length; i++)
	if (plugins_enabled[i])
	  {
	    Natura.logger.debug("[Plugin] preInit of " + plugins[i].name () + ".");
	    plugins_enabled[i] = plugins[i].loadable ();
	    if (plugins_enabled[i])
	    	plugins[i].preInit (evt);
	    else
	      Natura.logger.debug("[Plugin] " + plugins[i].name () + " was disabled.");
	  }
    }

    public static void init (FMLInitializationEvent evt)
    {
      for (int i = 0; i < plugins.length; i++)
	if (plugins_enabled[i])
	  {
	    Natura.logger.debug("[Plugin] init of " + plugins[i].name () + ".");
	    plugins[i].init (evt);
	  }
    }

    public static void postInit (FMLPostInitializationEvent evt)
    {
      for (int i = 0; i < plugins.length; i++)
	if (plugins_enabled[i])
	  {
	    Natura.logger.debug("[Plugin] postInit of " + plugins[i].name () + ".");
	    plugins[i].postInit (evt);
	  }
    }

    public static void config (Configuration cfg)
    {
      for (int i = 0; i < plugins.length; i++)
	plugins_enabled[i] = cfg.get("Plugins", "Enable " + plugins[i].name (), true).getBoolean(true);
    }
}
