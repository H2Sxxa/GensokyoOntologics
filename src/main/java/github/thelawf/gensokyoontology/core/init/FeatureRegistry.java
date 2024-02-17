package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.feature.WaterfallFeature;
import github.thelawf.gensokyoontology.common.world.feature.tree.MagicTreeFeature;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicTreeConfig;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public final class FeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(
            ForgeRegistries.FEATURES, GensokyoOntology.MODID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> MAGIC_TREE = FEATURES.register(
            "magic_tree_feature", () -> new MagicTreeFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<WaterfallFeature> WATERFALL = FEATURES.register(
            "water_fall", () -> new WaterfallFeature(LiquidsConfig.CODEC));

}
