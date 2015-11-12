package mods.natura.blocks;

import mods.natura.Natura;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class NStairs extends BlockStairs
{
    public NStairs(Block par2Block, int par3, String name)
    {
        super(par2Block, par3);
        this.setCreativeTab(Natura.tab);
        this.setStepSound(soundTypeWood);
        this.setLightOpacity(0);
        this.setBlockName("stair." + name);
    }
}
