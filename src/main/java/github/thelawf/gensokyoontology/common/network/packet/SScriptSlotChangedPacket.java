package github.thelawf.gensokyoontology.common.network.packet;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SScriptSlotChangedPacket {

    public SScriptSlotChangedPacket() {

    }

    public SScriptSlotChangedPacket(PacketBuffer buf) {

    }

    public void toBytes(PacketBuffer buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {

        });
        ctx.get().setPacketHandled(true);
    }
}