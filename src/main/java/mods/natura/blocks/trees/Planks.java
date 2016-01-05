package mods.natura.blocks.trees;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import mods.natura.blocks.NBlock;
import mods.natura.common.NContent;
import mods.natura.items.blocks.BarricadeItem;
import mods.natura.items.blocks.PlanksItem;

public class Planks extends NBlock
{
    public Planks()
    {
        super(Material.wood, 2.0f, NContent.append(NContent.woodTextureNames, "_planks"));
        this.setStepSound(Block.soundTypeWood);
        this.setBlockName("planks");
    }

	@Override
	public void reg() {
        GameRegistry.registerBlock(this, PlanksItem.class, "planks");
	}
}
