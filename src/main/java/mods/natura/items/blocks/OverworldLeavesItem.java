package mods.natura.items.blocks;

import java.util.List;

import mantle.blocks.abstracts.MultiItemBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OverworldLeavesItem extends ItemBlock
{
    public static final String blockType[] = {"maple", "silverbell", "purpleheart", "tiger"};

    public OverworldLeavesItem(Block i)
    {
        super(i);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName (ItemStack itemstack)
    {
    	return "block.leaves." + blockType[itemstack.getItemDamage() % blockType.length];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        switch (stack.getItemDamage() % 4)
        {
        case 0:
            list.add(StatCollector.translateToLocal("tooltip.tree7"));
            break;
        case 1:
            list.add(StatCollector.translateToLocal("tooltip.tree8"));
            break;
        case 2:
            list.add(StatCollector.translateToLocal("tooltip.tree9"));
            break;
        case 3:
            list.add(StatCollector.translateToLocal("tooltip.tree10"));
            break;
        }
    }
}
