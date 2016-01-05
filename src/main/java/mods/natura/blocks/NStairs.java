package mods.natura.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.natura.Natura;
import mods.natura.common.NContent;
import mods.natura.common.NReg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class NStairs extends BlockStairs implements NReg
{
	String name;
    public NStairs(Block par2Block, int par3, String name)
    {
        super(par2Block, par3);
        this.name = name;

        this.setCreativeTab(Natura.tab);
        this.setStepSound(soundTypeWood);
        this.setLightOpacity(0);
        this.setBlockName("stair." + name);
    }

	@Override
	public void reg() {
		GameRegistry.registerBlock(this, "stair." + this.name);
	}

	@Override
	public void regRecipe() {
    	GameRegistry.addRecipe(new ItemStack(this, 4, 0), "#  ", "## ", "###", '#', new ItemStack(NContent.planks, 1, i++));
	}

	@Override
	public void regOredict() {
		OreDictionary.registerOre("stairWood", new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE));
	}

	static int i = 0;
}
