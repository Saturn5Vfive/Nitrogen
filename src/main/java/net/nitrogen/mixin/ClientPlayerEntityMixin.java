package net.nitrogen.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.nitrogen.config.Commands;
import net.nitrogen.config.template.Command;
import net.nitrogen.utils.ChatUtils;

import java.util.Arrays;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable=true)
	public void onSendChatMessage(String message, CallbackInfo ci){
		if(message.toLowerCase().startsWith("-")){
			ci.cancel();
			String[] args = message.substring(1).split(" ");
			Command c = Commands.get(args[0]);
			if(c == null){
				ChatUtils.message("Unknown Command, use -help");
				return;
			}
			args = Arrays.copyOfRange(args, 1, args.length);
			c.call(args);
			return;
		}
	}
}
