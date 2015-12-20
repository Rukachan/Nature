package mods.natura.blocks.trees;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import mods.natura.blocks.NBlock;
import mods.natura.common.NContent;

public class Planks extends NBlock
{
    public Planks()
    {
        super(Material.wood, 2.0f, NContent.append(NContent.woodTextureNames, "_planks"));
        this.setStepSound(Block.soundTypeWood);
    }

	@Override
	public void reg() {
		// TODO Auto-generated method stub
	}
}
