package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class SpellConsoleTileEntity extends TileEntity {
    private final ItemStackHandler itemHandler = createItemHandler();
    private final LazyOptional<IItemHandler> optionalHandler = LazyOptional.of(() -> itemHandler);
    public static final TranslationTextComponent CONTAINER_NAME = GensokyoOntology.withTranslation("container.", ".spell_card_console.title");
    public SpellConsoleTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public int getSlotLimit(int slot) {
                if (slot >= 0 && slot < 25) {
                    return 1;
                } else {
                    return super.getSlotLimit(slot);
                }
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }
}
