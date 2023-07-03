package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.TrapDoorBlock;

public class FagusTrapdoor extends TrapDoorBlock {
    public FagusTrapdoor() {
        super(Properties.from(Blocks.OAK_TRAPDOOR).sound(SoundType.WOOD));
    }
}
