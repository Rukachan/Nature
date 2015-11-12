package mods.natura.items.blocks;

import mantle.blocks.abstracts.MultiItemBlock;
import net.minecraft.block.Block;

public class GrassSlabItem extends MultiItemBlock
{
    public static final String blockType[] = { "grass", "bluegrass", "autumngrass" };

    public GrassSlabItem(Block id)
    {
        super(id, "block.soil", "slab", blockType);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
}
