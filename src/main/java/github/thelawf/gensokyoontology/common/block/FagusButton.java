package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;

public class FagusButton extends WoodButtonBlock {
    public FagusButton() {
        super(Properties.from(Blocks.OAK_BUTTON).sound(SoundType.WOOD));
    }
}
