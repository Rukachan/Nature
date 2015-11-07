package mods.natura.plugins;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public interface IPluginBase
{
    String name ();
    boolean loadable ();

	/* remember to @Optional.Method (modid = modId) the following: */
    void preInit (FMLPreInitializationEvent evt);
    void init (FMLInitializationEvent evt);
    void postInit (FMLPostInitializationEvent evt);
}
