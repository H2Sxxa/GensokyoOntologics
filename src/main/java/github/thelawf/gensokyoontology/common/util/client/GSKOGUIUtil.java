package github.thelawf.gensokyoontology.common.util.client;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.io.File;
import java.util.*;

public class GSKOGUIUtil {

    public static List<List<ItemStack>> makeRecipes() {
        List<List<ItemStack>> recipes = new ArrayList<>();

        recipes.add(GSKOGUIUtil.createExtractorRecipe(
                new ItemStack(Items.ENDER_EYE),
                new ItemStack(ItemRegistry.GITSUNE_TUBE_FULL.get()),
                new ItemStack(ItemRegistry.TALES_OCCULT_BALL.get()),
                ItemStack.EMPTY,
                new ItemStack(ItemRegistry.OCCULT_BALL.get())));

        recipes.add(GSKOGUIUtil.createExtractorRecipe(
                new ItemStack(ItemRegistry.MAPLE_LEAVES_ITEM.get()),
                new ItemStack(ItemRegistry.ISHI_ZAKURA.get()),
                new ItemStack(ItemRegistry.ISHI_ZAKURA.get()),
                new ItemStack(ItemRegistry.ORB_JADE.get()),
                new ItemStack(ItemRegistry.AYA_FANS.get())));

        recipes.add(createExtractorRecipe(
                new ItemStack(ItemRegistry.JADE_LEVEL_SS.get()),
                new ItemStack(ItemRegistry.DRAGON_SPHERE.get()),
                new ItemStack(ItemRegistry.IZANO_OBJECT.get()),
                ItemStack.EMPTY,
                new ItemStack(ItemRegistry.ORB_JADE.get())));

        recipes.add(createExtractorRecipe(
                new ItemStack(ItemRegistry.CRIMSON_ALLOY_FRAGMENT.get()),
                new ItemStack(ItemRegistry.CRIMSON_ALLOY_FRAGMENT.get()),
                new ItemStack(ItemRegistry.CRIMSON_ALLOY_FRAGMENT.get()),
                new ItemStack(ItemRegistry.CRIMSON_ALLOY_FRAGMENT.get()),
                new ItemStack(ItemRegistry.CRIMSON_ALLOY_INGOT.get())));

        return recipes;
    }

    public static File withXMMUIFile(String xmlName){
        return new File(System.getProperty("user.dir").replace("run","") + "/src/main/resources/assets/gensokyoontology/xmmui/" + xmlName);
    }

    public static List<List<Integer>> makeDanmakuRecipes() {
        List<List<Integer>> recipeIndexes = new ArrayList<>();
        // 大型星弹的槽位
        recipeIndexes.add(createRecipeIndexes(2, 7, 10, 11, 12, 13, 14, 16, 18, 20, 24));
        // 心弹的槽位
        recipeIndexes.add(createRecipeIndexes(1, 3, 5, 7, 9, 10, 14, 16, 18, 22));
        // 大弹的槽位
        recipeIndexes.add(createRecipeIndexes(0, 1, 2, 3, 4, 5, 9, 10, 14, 15, 19, 20, 24));

        return recipeIndexes;
    }

    public static List<Integer> createRecipeIndexes(Integer... slots) {
        List<Integer> list = new ArrayList<>();
        return new ArrayList<>(Arrays.asList(slots));
    }

    public static List<ItemStack> createExtractorRecipe(ItemStack upSlot, ItemStack leftSlot,
                                                        ItemStack rightSlot, ItemStack downSlot, ItemStack result) {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.add(upSlot);
        stacks.add(leftSlot);
        stacks.add(rightSlot);
        stacks.add(downSlot);
        stacks.add(result);
        return stacks;
    }

}
