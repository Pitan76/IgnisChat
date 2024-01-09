package ml.pkom.ignischat.mc119.mixin;

import com.github.ucchyocean.lc3.japanize.IMEConverter;
import com.github.ucchyocean.lc3.japanize.YukiKanaConverter;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static ml.pkom.ignischat.common.IgnisChatAPI.*;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Redirect(method = "handleDecoratedMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/server/filter/FilteredMessage;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/util/registry/RegistryKey;)V"))
    public void redirectBroadcast(PlayerManager instance, FilteredMessage<SignedMessage> filteredMessage, ServerPlayerEntity serverPlayerEntity, RegistryKey<MessageType> registryKey) {
        String messageText = filteredMessage.raw().getContent().getString();

        if (messageText.startsWith("#")) {
            messageText = messageText.substring(1);
            instance.broadcast(FilteredMessage.censored(SignedMessage.of(Text.of(messageText))), serverPlayerEntity, registryKey);
            return;
        }

        if(!containsUnicode(messageText)) {
            messageText = messageText + " &6(" + IMEConverter.convByGoogleIME(YukiKanaConverter.conv(removeAmpersand(messageText))) + ")";
        }

        messageText = replaceAmpersand(messageText);

        instance.broadcast(FilteredMessage.censored(SignedMessage.of(Text.of(messageText))), serverPlayerEntity, registryKey);
    }
}
