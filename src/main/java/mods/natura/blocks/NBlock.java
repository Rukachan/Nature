package mods.natura.blocks;

import java.util.List;

import mods.natura.Natura;
import mods.natura.common.NReg;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class NBlock extends Block implements NReg
{
    public String[] textureNames;

    Block modelBlock;
    int meta, side;
    int subblocks;

    @SideOnly(Side.CLIENT)
    public IIcon[] icons;

    public NBlock(Material material, float hardness, String[] tex, int subblocks)
    {
        super(material);

        this.textureNames = tex;
        this.subblocks = subblocks;

        this.setHardness(hardness);
        this.setCreativeTab(Natura.tab);
    }

    public NBlock(Material material, float hardness, String[] tex)
    {
        this (material, hardness, tex, 0);
    }

    public NBlock(Material material, float hardness, Block model, int meta, int side, int subblocks)
    {
    	this(material, hardness, null);
    	this.modelBlock = model;
    	this.meta = meta;
    	this.side = side;
    	this.subblocks = subblocks;
    }

    @Override
    public int damageDropped (int meta)
    {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
    	if (this.textureNames != null)
    	{
	        this.icons = new IIcon[this.textureNames.length];
	
	        for (int i = 0; i < this.icons.length; i++)
	            this.icons[i] = iconRegister.registerIcon("natura:" + this.textureNames[i]);
    	}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta)
    {
        return this.icons != null ? this.icons[meta] : modelBlock != null ? modelBlock.getIcon(this.side == -1 ? side : this.side, this.meta == -1 ? meta : this.meta) : this.blockIcon;
    }

    @Override
    public void getSubBlocks (Item id, CreativeTabs tab, List list)
    {
    	int num = this.subblocks == 0 ? this.icons.length : this.subblocks;
        for (int i = 0; i < num; i++)
            list.add(new ItemStack(id, 1, i));
    }

    /* NReg */
	@Override
	public abstract void reg();

	@Override
	public void regRecipe() {
	}

	@Override
	public void regOredict() {
	}
}
