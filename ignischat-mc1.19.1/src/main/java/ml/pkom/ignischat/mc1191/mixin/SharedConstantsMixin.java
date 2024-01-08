package ml.pkom.ignischat.mc1191.mixin;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SharedConstants.class)
public class SharedConstantsMixin {

    // §をチャット上で使えるようにする
    @Inject(method = "isValidChar", at = @At("HEAD"), cancellable = true)
    private static void isValidChar(char chr, CallbackInfoReturnable<Boolean> cir) {
        if (chr == '§') {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
