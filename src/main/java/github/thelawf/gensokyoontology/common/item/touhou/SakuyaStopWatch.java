package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.network.CountDownNetworking;
import github.thelawf.gensokyoontology.common.network.packet.CountdownStartPacket;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

/**
 * 咲夜的怀表物品
 */
public class SakuyaStopWatch extends Item implements IRayTraceReader{
    private int pauseTicks = 100;
    private AtomicReference<Vector3d> vector3d = new AtomicReference<>(new Vector3d(0, 0, 0));
    private AtomicReference<Float> speed = new AtomicReference<>();
    public SakuyaStopWatch(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (!worldIn.isRemote() && stack.getOrCreateTag().getLong("cooldown") < worldIn.getGameTime()) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            BlockPos playerPos = playerIn.getPosition();

            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("pause_ticks", this.pauseTicks);
            stack.setTag(nbt);

        }
        if (playerIn.isCreative())
            return super.onItemRightClick(worldIn, playerIn, handIn);

        stack.getOrCreateTag().putLong("cooldown", worldIn.getGameTime() + 6000);
        playerIn.getCooldownTracker().setCooldown(stack.getItem(), 6000);
        return ActionResult.resultPass(stack);
    }

    private void freezeEntities(World world, Class<Entity> entityClass,@Nullable Predicate<Entity> predicate, AxisAlignedBB aabb, float radius) {
        if (predicate == null) {
            getEntityWithinSphere(world, entityClass, aabb, radius).forEach(entity -> entity.canUpdate(false));
            return;
        }
        getEntityWithinSphere(world, entityClass, predicate, aabb, radius).forEach(entity -> entity.canUpdate(false));
    }

    private void unfreezeEntities(World world, Class<Entity> entityClass,@Nullable Predicate<Entity> predicate, AxisAlignedBB aabb, float radius) {
        if (predicate == null) {
            getEntityWithinSphere(world, entityClass, aabb, radius).forEach(entity -> entity.canUpdate(true));
            return;
        }
        getEntityWithinSphere(world, entityClass, predicate, aabb, radius).forEach(entity -> entity.canUpdate(true));
    }

    @Override
    @NotNull
    public ActionResultType itemInteractionForEntity(@NotNull ItemStack stack, PlayerEntity playerIn, @NotNull LivingEntity target, @NotNull Hand hand) {

        if (!playerIn.getEntityWorld().isRemote) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();

            // 检测，如果目标实体允许更新则禁止其更新，反之亦然。
            target.canUpdate(!target.canUpdate());
            CountDownNetworking.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(
                    () -> target), new CountdownStartPacket(200, target, serverWorld.getDimensionKey()));
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, @NotNull ITooltipFlag flagIn) {
        tooltip.add(GensokyoOntology.withTranslation("tooltip.", ".sakuya_stop_watch"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BLOCK;
    }

}
