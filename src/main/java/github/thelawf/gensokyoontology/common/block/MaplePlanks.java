package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;

public class MaplePlanks extends Block {
    public MaplePlanks() {
        super(Properties.from(Blocks.OAK_PLANKS).sound(SoundType.WOOD));
    }
}
