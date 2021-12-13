package net.nitrogen.utils;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;
import net.nitrogen.Nitrogen;

public class Utils {
    public static Vec3d RenderCameraStart(){
		ClientPlayerEntity player = Nitrogen.i.player;
		Camera camera = Nitrogen.i.gameRenderer.getCamera();
		float f = 0.017453292F;
		float pi = (float)Math.PI;
		
		float f1 = (float) Math.cos(-player.getYaw() * f - pi);
		float f2 = (float) Math.sin(-player.getYaw() * f - pi);
		float f3 = (float) -Math.cos(-player.getPitch() * f);
		float f4 = (float) Math.sin(-player.getPitch() * f);
		
		return new Vec3d(f2 * f3, f4, f1 * f3).add(camera.getPos());
	}

	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static int lengthTo(Vec3d v){
        return (int) roundToN(v.distanceTo(Nitrogen.i.player.getPos()), 0);
    }

    public static double roundToN(double x, int n) {
        if (n == 0) return Math.floor(x);
        double factor = Math.pow(10, n);
        return Math.round(x * factor) / factor;
    }

	private static int random(){
		return (int) Math.floor(Math.random() * 10);
	}

	public static boolean ranged(int a, int b, int c){
		int d = Math.abs(a - b);
		if(d > c){
			return false;
		}else{
			return true;
		}
	}

    public static double randomRotation() {
        return (Math.random() - 0.5) * Math.PI * 2;
    }

	public static void loop(int amount, Runnable r){
		for(int i = 0; i < amount; i++){
			r.run();
		}
	}
}
