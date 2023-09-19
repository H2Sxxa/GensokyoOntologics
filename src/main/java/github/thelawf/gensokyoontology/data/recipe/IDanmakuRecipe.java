package github.thelawf.gensokyoontology.data.recipe;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface IDanmakuRecipe extends IRecipe<IInventory> {
    ResourceLocation RECIPE_ID = new ResourceLocation(GensokyoOntology.MODID, "danmaku");

    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOptional(RECIPE_ID).get();
    }

    @Override
    default boolean canFit(int width, int height){
        return true;
    }

    @Override
    default boolean isDynamic() {
        return true;
    }
}
