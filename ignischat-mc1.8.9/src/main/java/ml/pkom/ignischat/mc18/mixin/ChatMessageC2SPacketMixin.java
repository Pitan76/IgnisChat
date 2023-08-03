package ml.pkom.ignischat.mc18.mixin;

import com.github.ucchyocean.lc3.japanize.IMEConverter;
import com.github.ucchyocean.lc3.japanize.YukiKanaConverter;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static ml.pkom.ignischat.common.IgnisChatAPI.*;

@Mixin(ChatMessageC2SPacket.class)
public class ChatMessageC2SPacketMixin {

    @Shadow private String chatMessage;

    // 通常のチャットの処理メソッド
    @Inject(method = "getChatMessage", at = @At("HEAD"), cancellable = true)
    private void inject_getChatMessage(CallbackInfoReturnable<String> cir) {

        if (chatMessage == null) return;
        String string = chatMessage;

        if (string.startsWith("/")) {
            return;
        }

        if (string.startsWith("#")) {
            string = string.substring(1);
            cir.setReturnValue(string);
            return;
        }

        if (containsUnicode(string)) {
            cir.setReturnValue(replaceAmpersand(string));
            return;
        }

        string = string + " &6(" + IMEConverter.convByGoogleIME(YukiKanaConverter.conv(removeAmpersand(string))) + ")";
        cir.setReturnValue(replaceAmpersand(string));
    }
}
