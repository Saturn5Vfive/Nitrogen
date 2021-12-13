package net.nitrogen.gui.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.nitrogen.Nitrogen;
import net.nitrogen.utils.RenderUtils;
import net.nitrogen.utils.Utils;
import java.awt.Color;

public class PointRenderer {
    List<Point> cache = new ArrayList<Point>();

    public PointRenderer(int Points){
        for(int i = 0; i < Points; i++){
            Random random = new Random();
            Point Point = new Point(random.nextInt(Nitrogen.i.getWindow().getScaledWidth()), random.nextInt(Nitrogen.i.getWindow().getScaledHeight()), Utils.randomRotation(), 1);
            cache.add(Point);
        }
    }

    public void spawnBreak(double x, double y){
        Point Point = new Point(x, y, Utils.randomRotation(), 1);
        cache.add(Point);
    }

    public void tickPhysics(){
        for(Point n : cache){
            n.x += Math.cos(n.vector) * n.speed;
            n.y += Math.sin(n.vector) * n.speed;
            int flagcode = checkOOB(n);
            if(flagcode > 0){
                switch(flagcode){
                    case 1:
                    n.x = Nitrogen.i.getWindow().getScaledWidth();
                    n.vector = Math.toRadians(180 - Math.toDegrees(n.vector));
                    break;

                    case 2:
                    n.x = 0;
                    n.vector = Math.toRadians(180 - Math.toDegrees(n.vector));
                    break;
                    
                    case 3:
                    n.y = Nitrogen.i.getWindow().getScaledHeight();
                    n.vector = Math.toRadians(360 - Math.toDegrees(n.vector));
                    break;

                    case 4:
                    n.y = 0; 
                    n.vector = Math.toRadians(360 - Math.toDegrees(n.vector));
                    break;
                }
            }
        }
    }


    public void rebder(MatrixStack matrix){
        for(Point n : cache){
            //RenderUtils.circle2d(n.x + 1, n.y + 1, 2, new Color(125, 125, 125, 150), matrix, 90);
            for(Point nr : cache){
                if(nr == n) continue;
                double distance = distanceto(n.x + 1, n.y + 1, nr.x + 1, nr.y + 1);
                if(distance > 100) continue;
                double lum = (100 - distance) / 100;
                RenderUtils.lineScreenD(matrix, new Color(255, 255, 255, (int)(lum * 255)), n.x + 1, n.y + 1, nr.x + 1, nr.y + 1);
            }
        }
    }

    public int checkOOB(Point n){
        Window window = Nitrogen.i.getWindow();
        if(n.x > window.getScaledWidth()){
            return 1;
        }
        if(n.x < 0){
            return 2;
        }
        if(n.y > window.getScaledHeight()){
            return 3;
        }
        if(n.y < 0){
            return 4;
        }
        return 0;
    }

    public double distanceto(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
