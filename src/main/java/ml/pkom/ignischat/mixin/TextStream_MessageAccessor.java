package ml.pkom.ignischat.mixin;

import net.minecraft.server.filter.TextStream;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TextStream.Message.class)
public interface TextStream_MessageAccessor {
    @Accessor("raw")
    void setRaw(String string);
}
