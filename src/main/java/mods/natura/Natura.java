package mods.natura;

import java.util.Random;

import cpw.mods.fml.common.*;
import mods.natura.common.NContent;
import mods.natura.common.NProxyCommon;
import mods.natura.Natura;
import mods.natura.common.PHNatura;
import mods.natura.dimension.NetheriteWorldProvider;
import mods.natura.gui.NGuiHandler;
import mods.natura.worldgen.BaseCloudWorldgen;
import mods.natura.worldgen.BaseCropWorldgen;
import mods.natura.worldgen.BaseTreeWorldgen;
import mods.natura.worldgen.retro.TickHandlerWorld;
import mods.natura.worldgen.retro.WorldHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.natura.plugins.PluginManager;

@Mod(modid = Natura.MODID, name = Natura.NAME, version = Natura.VERSION, acceptedMinecraftVersions = "[1.7.10]")
public class Natura
{
    /* Proxies for sides, used for graphics processing */
    @SidedProxy(clientSide = "mods.natura.client.NProxyClient", serverSide = "mods.natura.common.NProxyCommon")
    public static NProxyCommon proxy;
    public static final String MODID = "Natura";
    public static final String NAME = "Natura";
    public static final String VERSION = "2.2.0";
    /* Instance of this mod, used for grabbing prototype fields */
    @Instance(MODID)
    public static Natura instance;
    public static Material cloud = new CloudMaterial();

    public static Logger logger = LogManager.getLogger(MODID);

    @EventHandler
    public void preInit (FMLPreInitializationEvent evt)
    {

        MinecraftForge.EVENT_BUS.register(this);

        PHNatura.initProps(evt.getSuggestedConfigurationFile());

        tab = new CreativeTabs("natura") {
        	@Override
        	@SideOnly(Side.CLIENT)
        	public Item getTabIconItem() {
        		return new ItemStack(NContent.plantItem, 0, 0).getItem(); /* r-right? */
        	}
        };

        content = new NContent();
        content.preInit();
        content.addOredictSupport();

        PluginManager.preInit(evt);
    }

    public static BaseCropWorldgen crops;
    public static BaseCloudWorldgen clouds;
    public static BaseTreeWorldgen trees;

    @EventHandler
    public void init (FMLInitializationEvent evt)
    {
        GameRegistry.registerWorldGenerator(crops = new BaseCropWorldgen(), 20); // TODO 1.7 Find correct weight (param 2)
        GameRegistry.registerWorldGenerator(clouds = new BaseCloudWorldgen(), 20); // TODO 1.7 Find correct weight (param 2)
        GameRegistry.registerWorldGenerator(trees = new BaseTreeWorldgen(), 20); // TODO 1.7 Find correct weight (param 2)

        proxy.registerRenderer();
        proxy.addNames();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new NGuiHandler());

        GameRegistry.registerFuelHandler(content);

        if (PHNatura.overrideNether)
        {
            DimensionManager.unregisterProviderType(-1);
            DimensionManager.registerProviderType(-1, NetheriteWorldProvider.class, true);
        }
        MinecraftForge.EVENT_BUS.register(WorldHandler.instance);

        if (retrogen)
        {
            FMLCommonHandler.instance().bus().register(new TickHandlerWorld());
        }

        OreDictionary.registerOre("cropVine", new ItemStack(NContent.thornVines));
        random.setSeed(2 ^ 16 + 2 ^ 8 + (4 * 3 * 271));

        PluginManager.init(evt);
    }

    @EventHandler
    public void postInit (FMLPostInitializationEvent evt)
    {
        content.createEntities();

        PluginManager.postInit(evt);
    }
    @SubscribeEvent
    public void bonemealEvent (BonemealEvent event)
    {
        if (!event.world.isRemote && !event.isCanceled() && event.getResult() != Result.ALLOW)
        {
            if (event.block == NContent.glowshroom)
            {
                if (NContent.glowshroom.fertilizeMushroom(event.world, event.x, event.y, event.z, event.world.rand))
                    event.setResult(Result.ALLOW);
            }
            if (event.block == NContent.berryBush)
            {
                if (NContent.berryBush.boneFertilize(event.world, event.x, event.y, event.z, event.world.rand))
                    event.setResult(Result.ALLOW);
            }
            if (event.block == NContent.netherBerryBush)
            {
                if (NContent.netherBerryBush.boneFertilize(event.world, event.x, event.y, event.z, event.world.rand))
                    event.setResult(Result.ALLOW);
            }
        }
    }

    @SubscribeEvent
    public void interactEvent (EntityInteractEvent event)
    {
        //if (event.target == null)
        if (event.target instanceof EntityCow || event.target instanceof EntitySheep)
        {
            ItemStack equipped = event.entityPlayer.getCurrentEquippedItem();
            EntityAnimal creature = (EntityAnimal) event.target;
            if (equipped != null && equipped.getItem() == NContent.plantItem && equipped.getItemDamage() == 0 && creature.getGrowingAge() == 0 && !creature.isInLove())
            {
                EntityPlayer player = event.entityPlayer;
                if (!player.capabilities.isCreativeMode)
                {
                    --equipped.stackSize;

                    if (equipped.stackSize <= 0)
                    {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    }
                }

                creature.func_146082_f(event.entityPlayer);
            }
        }
    }

    @SubscribeEvent
    public void spawnEvent (EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityCow || event.entity instanceof EntitySheep)
        {
            ((EntityLiving) event.entity).tasks.addTask(3, new EntityAITempt((EntityCreature) event.entity, 0.25F, NContent.plantItem, false));
        }

        if (event.entity instanceof EntityChicken)
        {
            ((EntityLiving) event.entity).tasks.addTask(3, new EntityAITempt((EntityCreature) event.entity, 0.25F, NContent.seeds, false));
        }
    }

    public static boolean retrogen;

    @SubscribeEvent
    public void chunkDataSave (ChunkDataEvent.Save event)
    {
        event.getData().setBoolean("Natura.Retrogen", true);
    }

    NContent content;
    public static Random random = new Random();
    public static CreativeTabs tab;
}
