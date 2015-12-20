package mods.natura.blocks;

import mods.natura.Natura;
import mods.natura.common.NContent;
import mods.natura.common.NReg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NFenceGate extends BlockFenceGate implements NReg
{
    Block modelBlock;
    int modelMeta;

    public NFenceGate(Block block, int meta, String name)
    {
        super();
        modelBlock = block;
        modelMeta = meta;
        this.setCreativeTab(Natura.tab);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeWood);
        this.setBlockName("fenceGate." + name);
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
		GameRegistry.registerBlock(this, "fenceGate." + this.getUnlocalizedName());
	}

	@Override
	public void regRecipe() {
    	GameRegistry.addRecipe(new ItemStack(this, 1, 0), "s#s", "s#s", '#', new ItemStack(NContent.planks, 1, i++), 's', Items.stick);
	}

	@Override
	public void regOredict() {
	}

	static int i = 0;
}
