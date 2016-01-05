package mods.natura.items.blocks;
/* Stolen directly from TiC */

import cpw.mods.fml.relauncher.*;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class BarricadeItem extends ItemBlock
{
	private Block b;

	public BarricadeItem(Block b)
	{
		super(b);
		this.b = b;
	}

	@Override
	public boolean onItemUse (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		Block b = world.getBlock(x, y, z);

		if (b == Blocks.snow && (world.getBlockMetadata(x, y, z) & 7) < 1)
			side = 1;
		else if (b != Blocks.vine && b != Blocks.tallgrass && b != Blocks.deadbush && (b == null || !b.canPlaceBlockAt(world, x, y, z)))
			switch (side)
			{
			case 0:
				--y;
				break;

			case 1:
				++y;
				break;

			case 2:
				--z;
				break;

			case 3:
				++z;
				break;

			case 4:
				--x;
				break;

			case 5:
				++x;
				break;
			}

		if (stack.stackSize == 0 || !player.canPlayerEdit(x, y, z, side, stack) || y == 255 && this.b.getMaterial().isSolid() || !world.canPlaceEntityOnSide(this.b, x, y, z, false, side, player, stack))
			return false;
		else
		{
			Block block = this.b;
			// int meta = this.getMetadata(stack.getItemDamage());
			int rotation = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			int meta = rotation * 4;
			int metadata = this.b.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);

			if (placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata))
			{
				world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), block.stepSound.soundName, (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				--stack.stackSize;
			}

			return true;
		}
	}
}
