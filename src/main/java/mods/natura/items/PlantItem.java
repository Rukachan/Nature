package mods.natura.items;

import java.util.List;

import mods.natura.Natura;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlantItem extends Item
{
    public String[] textureNames = {"barley_plant", "barley_flour", "wheat_flour", "cotton_plant", "sulfur", "ghostwood_fletching", "leather_imp", "flamestring", "dye_blue"};
    public String[] unlocalizedNames = {"barley.plant", "barley.flour", "wheat.flour", "cotton.plant", "powder.sulfur", "fletching.ghostwood", "leather.imp", "string.flame", "dye.blue"};
    public IIcon[] icons;

    public PlantItem()
    {
        super();

        this.setCreativeTab(Natura.tab);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName (ItemStack itemstack)
    {
        return (new StringBuilder()).append("item.").append(unlocalizedNames[itemstack.getItemDamage()]).toString();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        switch (stack.getItemDamage())
        {
        case 0:
            list.add(StatCollector.translateToLocal("tooltip.barley"));
            break;
        case 1:
        case 2:
            list.add(StatCollector.translateToLocal("tooltip.flour1"));
            list.add(StatCollector.translateToLocal("tooltip.flour2"));
            break;
        case 3:
            list.add(StatCollector.translateToLocal("tooltip.cotton"));
            break;
        case 4:
            list.add(StatCollector.translateToLocal("tooltip.sulfur"));
            break;
        case 5:
            list.add(StatCollector.translateToLocal("tooltip.fletching"));
            break;
        case 6:
            list.add(StatCollector.translateToLocal("tooltip.imp"));
            break;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage (int meta)
    {
        return icons[meta];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons (IIconRegister iconRegister)
    {
        this.icons = new IIcon[textureNames.length];

        for (int i = 0; i < this.icons.length; ++i)
            this.icons[i] = iconRegister.registerIcon("natura:" + textureNames[i]);
    }

    @Override
    public void getSubItems (Item id, CreativeTabs tab, List list)
    {
        for (int i = 0; i < unlocalizedNames.length; i++)
            list.add(new ItemStack(id, 1, i));
    }
}
