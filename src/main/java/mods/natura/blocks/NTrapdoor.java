package mods.natura.blocks;

import mods.natura.Natura;
import mods.natura.common.NContent;
import mods.natura.common.NReg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NTrapdoor extends BlockTrapDoor implements NReg
{
    String name;

    public NTrapdoor(String name)
    {
        super(Material.wood);
        this.name = name;

        this.setHardness(3.0F);
        this.setStepSound(Block.soundTypeWood);
        this.setCreativeTab(Natura.tab);
        this.setBlockName("trapdoor." + name);
        this.disableStats();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon("natura:" + this.name + "_trapdoor");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta)
    {
        return this.blockIcon;
    }

	@Override
	public void reg() {
        GameRegistry.registerBlock(this, "trapdoor." + this.name);
	}

	@Override
	public void regRecipe() {
    	GameRegistry.addRecipe(new ItemStack(this, 2, 0), "###", "###", '#', new ItemStack(NContent.planks, 1, i++));
	}

	@Override
	public void regOredict() {
	}

	static int i = 0;
}
