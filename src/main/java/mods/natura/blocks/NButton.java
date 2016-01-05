package mods.natura.blocks;

import mods.natura.Natura;
import mods.natura.common.NContent;
import mods.natura.common.NReg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NButton extends BlockButton implements NReg
{
    Block modelBlock;
    int modelMeta;
    String name;

    public NButton(Block block, int meta, String name)
    {
        super(true);
        this.modelBlock = block;
        this.modelMeta = meta;
        this.name = name;

        this.setCreativeTab(Natura.tab);
    	this.setHardness(0.5F);
    	this.setStepSound(Block.soundTypeWood);
    	this.setBlockName("button." + name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta)
    {
        return modelBlock.getIcon(side, modelMeta);
    }

	@Override
	public void reg() {
		GameRegistry.registerBlock(this, "button." + this.name);
	}

	@Override
	public void regRecipe() {
    	GameRegistry.addRecipe(new ItemStack(this, 1, 0), "#", '#', new ItemStack(NContent.planks, 1, i++));
	}

	@Override
	public void regOredict() {
	}

	static int i = 0;
}
