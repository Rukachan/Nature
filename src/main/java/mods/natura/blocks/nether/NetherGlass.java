package mods.natura.blocks.nether;

import java.util.List;
import java.util.Random;

import mods.natura.blocks.NBlock;
import mods.natura.common.NContent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NetherGlass extends NBlock
{
    @SideOnly(Side.CLIENT)
    public IIcon[] icons;

    public NetherGlass()
    {
        super(Material.glass, 0.3f, new String[] {"glass_soul", "glass_heat", "glass_soul_item", "glass_heat_item"}, 2);
    }

    @Override
    public int quantityDropped (Random par1Random)
    {
        return 0;
    }

    @Override
    public boolean isOpaqueCube ()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock ()
    {
        return false;
    }

    @Override
    protected boolean canSilkHarvest ()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered (IBlockAccess par1, int par2, int par3, int par4, int par5)
    {
        Block i1 = par1.getBlock(par2, par3, par4);
        return i1 == this ? false : super.shouldSideBeRendered(par1, par2, par3, par4, par5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass ()
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        icons = new IIcon[] {iconRegister.registerIcon("natura:glass_soul"),
        					 iconRegister.registerIcon("natura:glass_heat"),
        					 iconRegister.registerIcon("natura:glass_soul_item"),
        					 iconRegister.registerIcon("natura:glass_heat_item")};
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (IBlockAccess world, int x, int y, int z, int side)
    {
        return icons[world.getBlockMetadata(x, y, z) < 1 ? 0 : 1];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta)
    {
        return icons[meta < 1 ? 2 : 3];
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return meta == 0 ? null : meta == 1 ? AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - 0.125F, z + 1) : super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void onEntityCollidedWithBlock (World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            int meta = world.getBlockMetadata(x, y, z);
            if (meta == 0)
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 1));
            else if (meta == 1)
                NContent.heatSand.onEntityCollidedWithBlock(world, x, y, z, entity);
        }
    }

	@Override
	public void reg() {
		// TODO Auto-generated method stub
	}
}
