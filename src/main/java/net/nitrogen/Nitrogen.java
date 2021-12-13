package net.nitrogen;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

public class Nitrogen implements ModInitializer {

	public static final MinecraftClient i = MinecraftClient.getInstance();

	@Override
	public void onInitialize() {
		System.out.println("Nitrogen Loaded");
	}
}
