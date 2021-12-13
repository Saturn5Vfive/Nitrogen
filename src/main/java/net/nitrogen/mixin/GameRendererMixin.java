package net.nitrogen.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.objectweb.asm.Opcodes;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.nitrogen.config.Modules;
import net.nitrogen.config.template.Module;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(at = {@At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode = Opcodes.GETFIELD, ordinal = 0)}, method = {"renderWorld(FJLnet/minecraft/client/util/math/MatrixStack;)V"})
	private void onRenderWorld(float partialTicks, long donetype, MatrixStack matrix, CallbackInfo ci)
	{
        for(Module mod : Modules.getModules()){
            if(mod.enabled()){
                mod.onRenderWorld(matrix, partialTicks);
            }
        }
	}
}