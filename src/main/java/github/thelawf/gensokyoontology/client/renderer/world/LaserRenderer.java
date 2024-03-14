package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;

@OnlyIn(Dist.CLIENT)
public class LaserRenderer {

    public static final ResourceLocation BACON_BEAM_TEX = new ResourceLocation("minecraft:textures/particles/beacon");


    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, Vector3f pos, Vector3f start, Vector3f end) {
        builder.pos(matrix4f, pos.getX() + start.getX(), pos.getY() + start.getY(), pos.getZ() + start.getZ())
                .color(1F, 0F, 0F, 0F).endVertex();
        builder.pos(matrix4f, pos.getX() + end.getX(), pos.getY() + end.getY(), pos.getZ() + end.getZ())
                .color(1F, 0F, 0F, 0F).endVertex();
    }

    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, Vector3f start, Vector3f end, float r, float g, float b, float alpha) {
        builder.pos(matrix4f, start.getX(), start.getY(), start.getZ()).color(r, g, b, alpha).endVertex();
        builder.pos(matrix4f, end.getX(), end.getY(), end.getZ()).color(r, g, b, alpha).endVertex();
    }

    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
        builder.pos(matrix4f, dx1, dy1, dz1).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        builder.pos(matrix4f, dx2, dy2, dz2).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
    }

    private static Vector3f toVector3f(Vector3d vector3d) {
        return new Vector3f((float) vector3d.x, (float) vector3d.y, (float) vector3d.z);
    }

    public static void onRenderThirdPerson(RenderLivingEvent.Post<?,?> event) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        if (player == null) return;
        if (player.getHeldItemMainhand().getItem() != ItemRegistry.KOISHI_EYE_OPEN.get()) return;
        LaserRenderer.renderThirdPersonView(event);
    }


    public static void renderThirdPersonView(RenderLivingEvent.Post<?, ?> event) {
        if (event.getEntity() instanceof PlayerEntity) {
            IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
            IVertexBuilder builder = buffer.getBuffer(GSKORenderTypes.LASER_DIFFUSE);
            //GuardianRenderer

            MatrixStack matrixStack = event.getMatrixStack();
            Matrix4f matrix4f = matrixStack.getLast().getMatrix();

            matrixStack.push();
            drawLaser(builder, matrix4f, 0.5F, -2F, 0F, 0.5F, 2F, 0F);
            matrixStack.pop();
        }

        // if (player.ticksExisted % 10 == 0) player.sendChatMessage("Playe Look AT -- " + player.getLookVec());
    }
}
