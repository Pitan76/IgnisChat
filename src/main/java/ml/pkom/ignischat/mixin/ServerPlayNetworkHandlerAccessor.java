package ml.pkom.ignischat.mixin;

import net.minecraft.server.filter.TextStream;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Consumer;

@Mixin(ServerPlayNetworkHandler.class)
public interface ServerPlayNetworkHandlerAccessor {
    // メソッドへのアクセス用
    @Invoker("handleMessage")
    void handleMessage(TextStream.Message message);
}
