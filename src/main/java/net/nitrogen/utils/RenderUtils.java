package net.nitrogen.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.nitrogen.Nitrogen;

import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Random;


public class RenderUtils {
    public static void renderEntity(Entity e, Color c, MatrixStack matrix){
        Box box = e.getBoundingBox();
        Vec3d bang = box.getCenter().subtract(box.getXLength() / 2, box.getYLength() / 2, box.getZLength() / 2);
        renderObject(bang, new Vec3d(box.getXLength(), box.getYLength(), box.getZLength()), c, matrix);
    }

    public static void renderEntity(Entity e, Vec3d center, Color c, MatrixStack matrix){
        Box box = e.getBoundingBox();
        renderObject(center.subtract(box.getXLength() / 2, 0, box.getZLength() / 2), new Vec3d(box.getXLength(), box.getYLength(), box.getZLength()), c, matrix);
    }

    public static void renderObject(Vec3d home, Vec3d dimensions, Color color, MatrixStack stack) {
        float red = color.getRed() / 255f;
        float green = color.getGreen() / 255f;
        float blue = color.getBlue() / 255f;
        float alpha = color.getAlpha() / 255f;
        Camera c = Nitrogen.i.gameRenderer.getCamera();
        Vec3d camPos = c.getPos();
        home = home.subtract(camPos);
        Vec3d end = home.add(dimensions);
        Matrix4f matrix = stack.peek().getModel();
        float x1 = (float) home.x;
        float y1 = (float) home.y;
        float z1 = (float) home.z;
        float x2 = (float) end.x;
        float y2 = (float) end.y;
        float z2 = (float) end.z;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        GL11.glDepthFunc(GL11.GL_ALWAYS);
        RenderSystem.setShaderColor(red, green, blue, alpha);
        RenderSystem.enableBlend();
        buffer.begin(VertexFormat.DrawMode.QUADS,
                VertexFormats.POSITION);
        buffer.vertex(matrix, x1, y2, z1).next();
        buffer.vertex(matrix, x1, y2, z2).next();
        buffer.vertex(matrix, x2, y2, z2).next();
        buffer.vertex(matrix, x2, y2, z1).next();

        buffer.vertex(matrix, x1, y1, z2).next();
        buffer.vertex(matrix, x2, y1, z2).next();
        buffer.vertex(matrix, x2, y2, z2).next();
        buffer.vertex(matrix, x1, y2, z2).next();

        buffer.vertex(matrix, x2, y2, z2).next();
        buffer.vertex(matrix, x2, y1, z2).next();
        buffer.vertex(matrix, x2, y1, z1).next();
        buffer.vertex(matrix, x2, y2, z1).next();

        buffer.vertex(matrix, x2, y2, z1).next();
        buffer.vertex(matrix, x2, y1, z1).next();
        buffer.vertex(matrix, x1, y1, z1).next();
        buffer.vertex(matrix, x1, y2, z1).next();

        buffer.vertex(matrix, x1, y2, z1).next();
        buffer.vertex(matrix, x1, y1, z1).next();
        buffer.vertex(matrix, x1, y1, z2).next();
        buffer.vertex(matrix, x1, y2, z2).next();

        buffer.vertex(matrix, x1, y1, z1).next();
        buffer.vertex(matrix, x2, y1, z1).next();
        buffer.vertex(matrix, x2, y1, z2).next();
        buffer.vertex(matrix, x1, y1, z2).next();

        buffer.end();

        BufferRenderer.draw(buffer);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        RenderSystem.disableBlend();
    }


    public static void vector(Vec3d start, Vec3d end, Color color, MatrixStack matrices, int thicc) {
        float red = color.getRed() / 255f;
        float green = color.getGreen() / 255f;
        float blue = color.getBlue() / 255f;
        float alpha = color.getAlpha() / 255f;
        Camera c = Nitrogen.i.gameRenderer.getCamera();
        Vec3d camPos = c.getPos();
        start = start.subtract(camPos);
        end = end.subtract(camPos);
        Matrix4f matrix = matrices.peek().getModel();
        float x1 = (float) start.x;
        float y1 = (float) start.y;
        float z1 = (float) start.z;
        float x2 = (float) end.x;
        float y2 = (float) end.y;
        float z2 = (float) end.z;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        GL11.glDepthFunc(GL11.GL_ALWAYS);
        RenderSystem.setShaderColor(red, green, blue, alpha);
        RenderSystem.enableBlend();
        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES,
                VertexFormats.POSITION);

        buffer.vertex(matrix, x1, y1, z1).next();
        buffer.vertex(matrix, x2, y2, z2).next();

        buffer.end();

        BufferRenderer.draw(buffer);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        RenderSystem.disableBlend();
    }

    public static void lineScreenD(MatrixStack matrices, Color c, double x, double y, double x1, double y1) {
        float g = c.getRed() / 255f;
        float h = c.getGreen() / 255f;
        float k = c.getBlue() / 255f;
        float f = c.getAlpha() / 255f;
        Matrix4f m = matrices.peek().getModel();
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(m, (float) x, (float) y, 0f).color(g, h, k, f).next();
        bufferBuilder.vertex(m, (float) x1, (float) y1, 0f).color(g, h, k, f).next();
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void circle2d(double x, double y, double r, Color c, MatrixStack stack, double shitpc){
        int color = c.getRGB();
        Matrix4f matrix = stack.peek().getModel();
        float f = (float) (color >> 24 & 255) / 255.0F;
        float g = (float) (color >> 16 & 255) / 255.0F;
        float h = (float) (color >> 8 & 255) / 255.0F;
        float k = (float) (color & 255) / 255.0F;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableBlend();
        RenderSystem.enableCull();
        RenderSystem.disableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        buffer.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);
        for (double i = 0; i < 360; i += shitpc) {
            double radians = Math.toRadians(i);
            double sin = Math.sin(radians) * r;
            double cos = Math.cos(radians) * r;
            buffer.vertex(matrix, (float) (x + sin), (float) (y + cos), 0.0F).color(g, h, k, f).next();
        }
        buffer.end();
        BufferRenderer.draw(buffer);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.disableCull();
    }

    public static void fill(MatrixStack matrices, Color c, double x1, double y1, double x2, double y2) {
        int color = c.getRGB();
        double j;
        if (x1 < x2) {
            j = x1;
            x1 = x2;
            x2 = j;
        }

        if (y1 < y2) {
            j = y1;
            y1 = y2;
            y2 = j;
        }
        Matrix4f matrix = matrices.peek().getModel();
        float f = (float) (color >> 24 & 255) / 255.0F;
        float g = (float) (color >> 16 & 255) / 255.0F;
        float h = (float) (color >> 8 & 255) / 255.0F;
        float k = (float) (color & 255) / 255.0F;
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix, (float) x1, (float) y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float) x2, (float) y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float) x2, (float) y1, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float) x1, (float) y1, 0.0F).color(g, h, k, f).next();
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void renderRoundedQuad(MatrixStack matrices, Color c, double fromX, double fromY, double toX, double toY, int rad) {
        int color = c.getRGB();
        Matrix4f matrix = matrices.peek().getModel();
        float f = (float) (color >> 24 & 255) / 255.0F;
        float g = (float) (color >> 16 & 255) / 255.0F;
        float h = (float) (color >> 8 & 255) / 255.0F;
        float k = (float) (color & 255) / 255.0F;
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);

        double toX1 = toX - rad;
        double toY1 = toY - rad;
        double fromX1 = fromX + rad;
        double fromY1 = fromY + rad;
        int initial = -90;
        double[][] map = new double[][]{
                new double[]{toX1, toY1},
                new double[]{toX1, fromY1},
                new double[]{fromX1, fromY1},
                new double[]{fromX1, toY1}
        };
        for (int i = 0; i < 4; i++) {
            double[] current = map[i];
            initial += 90;
            for (int r = initial; r < (360 / 4 + initial); r++) {
                float rad1 = (float) Math.toRadians(r);
                float sin = (float) (Math.sin(rad1) * rad);
                float cos = (float) (Math.cos(rad1) * rad);
                bufferBuilder.vertex(matrix, (float) current[0] + sin, (float) current[1] + cos, 0.0F).color(g, h, k, f).next();
            }
        }
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }
}