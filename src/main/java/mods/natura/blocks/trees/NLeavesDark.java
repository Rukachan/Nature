package mods.natura.blocks.trees;

import java.util.List;
import java.util.Random;

import mods.natura.common.NContent;
import mods.natura.Natura;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NLeavesDark extends NLeaves
{
    public NLeavesDark()
    {
        super();
        this.setBlockName("Darkleaves");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        String[] textureNames = {"darkwood", "darkwood_flowering", "darkwood_fruit", "fusewood"};
        this.fastIcons = new IIcon[textureNames.length];
        this.fancyIcons = new IIcon[textureNames.length];

        for (int i = 0; i < this.fastIcons.length; i++)
        {
            this.fastIcons[i] = iconRegister.registerIcon("natura:" + textureNames[i] + "_leaves_fast");
            this.fancyIcons[i] = iconRegister.registerIcon("natura:" + textureNames[i] + "_leaves_fancy");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int metadata)
    {
        int meta = metadata % 4;

        return (field_150121_P ? fancyIcons : fastIcons)[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor ()
    {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor (int par1)
    {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier (IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return 0xFFFFFF;
    }

    public int getFlammability (IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return 0;
    }

    public int getFireSpreadSpeed (World world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return 0;
    }

    @Override
    public Item getItemDropped (int metadata, Random random, int fortune)
    {
    	return metadata % 4 == 2 ? NContent.potashApple : Item.getItemFromBlock(NContent.netherSapling);
    }

    @Override
    public int damageDropped (int meta)
    {
    	return meta % 4 == 2 ? 0 : meta % 4 == 3 ? 7 : 6;
    }

    @Override
    public int quantityDropped (int meta, int fortune, Random random)
    {
    	return meta % 4 == 2 ? 1 : quantityDroppedWithBonus(fortune, random);
    }

    @Override
    public void getSubBlocks (Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int i = 0; i < 4; i++)
    		par3List.add(new ItemStack(par1, 1, i));
    }

    @Override
    public int getLightOpacity (IBlockAccess world, int x, int y, int z)
    {
        return this.getLightOpacity();
    }
}
