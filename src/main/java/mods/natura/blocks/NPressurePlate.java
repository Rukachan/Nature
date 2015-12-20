package mods.natura.blocks;

import mods.natura.Natura;
import mods.natura.common.NContent;
import mods.natura.common.NReg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NPressurePlate extends BlockPressurePlate implements NReg
{
    Block modelBlock;
    int modelMeta;

    public NPressurePlate(Material material, Sensitivity s, Block block, int meta, String name)
    {
        super("", material, s);
        modelBlock = block;
        modelMeta = meta;
        this.setCreativeTab(Natura.tab);
        this.setHardness(0.5F);
        this.setStepSound(Block.soundTypeWood);
        this.setBlockName("pressureplate." + name);
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
		GameRegistry.registerBlock(this, "pressureplate." + this.getUnlocalizedName());
	}

	@Override
	public void regRecipe() {
    	GameRegistry.addRecipe(new ItemStack(this, 1, 0), "##", '#', new ItemStack(NContent.planks, 1, i++));
	}

	@Override
	public void regOredict() {
        OreDictionary.registerOre("pressurePlateWood", new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE));
	}

	static int i = 0;
}
