package github.thelawf.gensokyoontology.client.settings;

import com.google.common.collect.Lists;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public class GSKOKeyboardManager {

    public static int RENDER_TICK = 80;

    public static final KeyBinding MOUSE_RIGHT = new GSKOKeyBinding("mouse_right", KeyConflictContext.IN_GAME,
            InputMappings.Type.MOUSE, 1, GensokyoOntology.withAffix("key.category.",""));
    public static final KeyBinding EYE_BOX_FORWARD = new GSKOKeyBinding("eye_box_forward", KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM, 265, GensokyoOntology.withAffix("key.category.",""));
    public static final KeyBinding EYE_BOX_BACKWARD = new GSKOKeyBinding("eye_box_backward", KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM, 264, GensokyoOntology.withAffix("key.category.",""));
    public static final KeyBinding KEY_SWITCH_MODE = new GSKOKeyBinding("switch_mode", KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, GensokyoOntology.withAffix("key.category.",""));
    public static final List<KeyBinding> KEY_BINDINGS = Lists.newArrayList(KEY_SWITCH_MODE);

    public static void register() {
        for (KeyBinding key : KEY_BINDINGS) {
            ClientRegistry.registerKeyBinding(key);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void onSummonDestructiveEye(InputEvent.MouseInputEvent event) {
        // if (MOUSE_RIGHT.isPressed()) {
        //     Minecraft mc = Minecraft.getInstance();
        //     ClientPlayerEntity player = mc.player;
        //     if (player != null && count == 0 && player.getHeldItemMainhand().getItem() == ItemRegistry.LAEVATEIN_ITEM.get()) {
        //         trySpawnFromClient(player);
        //         setEyeBoxFromClient(player);
        //         count = 1;
        //     }
        // }
        // else if (MOUSE_RIGHT.isPressed()) {
        //     Minecraft mc = Minecraft.getInstance();
        //     ClientPlayerEntity player = mc.player;
        //     if (player != null && count == 1 && player.getHeldItemMainhand().getItem() == ItemRegistry.LAEVATEIN_ITEM.get()) {
        //         activateEye(player);
        //         count = 0;
        //     }
        // }
    }

    private static void trySpawnFromClient(ClientPlayerEntity player) {
        if (player.world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) player.world;
            EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get().spawn(serverWorld, null, null, player.getPosition(), SpawnReason.MOB_SUMMONED, false, false);
            serverWorld.getEntities().filter(entity -> entity.getType() == EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get()).forEach(entity -> {
                entity.canUpdate(false);
            });
        }
    }
    private static void setEyeBoxFromClient(ClientPlayerEntity player) {
        if (player.world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) player.world;
            Vector3d lookVec = player.getLookVec();
            if (EYE_BOX_FORWARD.isPressed()) {
                serverWorld.getEntities().filter(entity -> entity.getType() == EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get()).forEach(entity -> {
                    Vector3d newPos = entity.getPositionVec().add(lookVec.scale(0.217));
                    entity.setPosition(newPos.x, newPos.y, newPos.z);
                });
            }
            else if (EYE_BOX_BACKWARD.isPressed()) {
                serverWorld.getEntities().filter(entity -> entity.getType() == EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get())
                        .collect(Collectors.toList()).forEach(entity -> {
                            Vector3d newPos = entity.getPositionVec().add(lookVec.scale(-0.217));
                            entity.setPosition(newPos.x, newPos.y, newPos.z);
                        });
            }
        }
    }

    private static void activateEye(ClientPlayerEntity player) {
        if (player.world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) player.world;
            serverWorld.getEntities().filter(entity -> entity.getType() == EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get()).forEach(entity -> {
                entity.canUpdate(true);
            });
        }
    }
}
