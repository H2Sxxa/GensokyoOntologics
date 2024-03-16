package github.thelawf.gensokyoontology.common.item.script;

import com.mojang.serialization.Dynamic;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public abstract class DynamicScriptItem extends Item {
    public DynamicScriptItem() {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // this.addDynamicData(worldIn, playerIn, playerIn.getHeldItem(handIn), Dynamic.);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public abstract void addDynamicData(World world, PlayerEntity player, ItemStack stack, Dynamic<INBT> dynamic);

    public abstract void openScriptEditGUI(World world, PlayerEntity player, ItemStack stack);
}
