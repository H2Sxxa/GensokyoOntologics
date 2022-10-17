package github.thelawf.gensokyoontology.common.item.food;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BurgerMeatRaw extends Item {
    private static final Food food = new Food.Builder()
            .saturation(2)
            .hunger(-2)
            .meat()
            .build();

    public BurgerMeatRaw() {
        super(new Properties().group(ItemGroup.FOOD).food(food));
    }
}
