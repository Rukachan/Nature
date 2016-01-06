package mods.natura.items.blocks;

import java.util.List;

import mods.natura.common.NContent;
import mods.natura.common.NReg;
import mods.natura.Natura;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NDoorItem extends Item implements NReg
{
    public IIcon[] icons;
    final static public String[] textureNames = {"redwood", "eucalyptus", "hopseed", "sakura", "ghostwood", "bloodwood", "redwoodbark"};

    public NDoorItem()
    {
        super();
        maxStackSize = 64;
        setCreativeTab(Natura.tab);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName (ItemStack itemstack)
    {
        return (new StringBuilder()).append(textureNames[itemstack.getItemDamage()]).append("NDoor").toString();
    }

    @Override
    public boolean onItemUse (ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ)
    {
        if (side != 1)
            return false;
        y++;

        Block block = NContent.doors[itemstack.getItemDamage()];
        if (!player.canPlayerEdit(x, y, z, side, itemstack) || !player.canPlayerEdit(x, y + 1, z, side, itemstack) || !block.canPlaceBlockAt(world, x, y, z))
            return false;
        else
        {
            int rotate = MathHelper.floor_double(((player.rotationYaw + 180F) * 4F) / 360F - 0.5D) & 3;
            placeDoorBlock(world, x, y, z, rotate, block);
            itemstack.stackSize--;
            return true;
        }
    }

    public static void placeDoorBlock (World world, int x, int y, int z, int rotate, Block block)
    {
        int var6 = rotate == 1 ? -1 : rotate == 3 ? 1 : 0;
        int var7 = rotate == 0 ? 1 : rotate == 2 ? -1 : 0;

        int var8 = (world.getBlock(x - var6, y, z - var7).isNormalCube() ? 1 : 0) + (world.getBlock(x - var6, y + 1, z - var7).isNormalCube() ? 1 : 0);
        int var9 = (world.getBlock(x + var6, y, z + var7).isNormalCube() ? 1 : 0) + (world.getBlock(x + var6, y + 1, z + var7).isNormalCube() ? 1 : 0);
        boolean var10 = world.getBlock(x - var6, y, z - var7) == block || world.getBlock(x - var6, y + 1, z - var7) == block;
        boolean var11 = world.getBlock(x + var6, y, z + var7) == block || world.getBlock(x + var6, y + 1, z + var7) == block;

        world.setBlock(x, y, z, block, rotate, 2);
        world.setBlock(x, y + 1, z, block, 8 | (var10 && !var11 || var9 > var8 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, block);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, block);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage (int meta)
    {
        return icons[meta];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons (IIconRegister iconRegister)
    {
        this.icons = new IIcon[textureNames.length];

        for (int i = 0; i < this.icons.length; ++i)
            this.icons[i] = iconRegister.registerIcon("natura:" + textureNames[i] + "_door_item");
    }

    @Override
    public void getSubItems (Item id, CreativeTabs tab, List list)
    {
        for (int i = 0; i < textureNames.length; i++)
            list.add(new ItemStack(id, 1, i));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        switch (stack.getItemDamage())
        {
        case 0:
            list.add(StatCollector.translateToLocal("tooltip.tree4"));
            break;
        case 1:
            list.add(StatCollector.translateToLocal("tooltip.tree1"));
            break;
        case 2:
            list.add(StatCollector.translateToLocal("tooltip.tree6"));
            break;
        case 3:
            list.add(StatCollector.translateToLocal("tooltip.tree2"));
            break;
        case 4:
            list.add(StatCollector.translateToLocal("tooltip.tree3"));
            break;
        case 5:
            list.add(StatCollector.translateToLocal("tooltip.firedoor"));
            break;
        case 6:
            list.add(StatCollector.translateToLocal("tooltip.barkdoor"));
            break;
        }
    }

	@Override
	public void reg() {
        GameRegistry.registerItem(this, "redwoodDoorItem");
	}

	@Override
	public void regRecipe() {
	}

	@Override
	public void regOredict() {
	}
}
