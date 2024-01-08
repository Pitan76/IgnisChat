package ml.pkom.ignischat.mc1193.mixin;

import com.github.ucchyocean.lc3.japanize.IMEConverter;
import com.github.ucchyocean.lc3.japanize.YukiKanaConverter;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static ml.pkom.ignischat.common.IgnisChatAPI.*;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Redirect(method = "handleDecoratedMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/network/message/SignedMessage;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/network/message/MessageType$Parameters;)V"))
    public void redirectBroadcast(PlayerManager instance, SignedMessage signedMessage, ServerPlayerEntity serverPlayerEntity, MessageType.Parameters parameters) {
        String messageText = signedMessage.getSignedContent();

        if (messageText.startsWith("#")) {
            messageText = messageText.substring(1);
            instance.broadcast(SignedMessage.ofUnsigned(messageText), serverPlayerEntity, parameters);
            return;
        }

        if(!containsUnicode(messageText)) {
            messageText = messageText + " &6(" + IMEConverter.convByGoogleIME(YukiKanaConverter.conv(removeAmpersand(messageText))) + ")";
        }

        messageText = replaceAmpersand(messageText);

        instance.broadcast(SignedMessage.ofUnsigned(messageText), serverPlayerEntity, parameters);
    }
}
