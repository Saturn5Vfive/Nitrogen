package net.nitrogen.gui.imgui;

import org.spongepowered.asm.mixin.gen.Accessor;

import imgui.ImGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.nitrogen.Nitrogen;
import net.nitrogen.gui.other.PointRenderer;
import net.nitrogen.utils.RenderUtils;

import java.awt.Color;

public abstract class ProxyScreen extends Screen {
    boolean a = false;
    public static PointRenderer points;

    public ProxyScreen() {
        super(Text.of(""));
        points = new PointRenderer(100);
        ImGuiManager.init();
        this.a = false;
    }

    protected abstract void startWindow();
    protected abstract void renderInternal();

    @Override 
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderUtils.fill(matrices, new Color(25, 25, 25, 120), 0, 0, Nitrogen.i.getWindow().getScaledWidth(), Nitrogen.i.getWindow().getScaledHeight());
        points.rebder(matrices);
        points.tickPhysics();
        ImGui.getIO().setDisplaySize(Nitrogen.i.getWindow().getWidth(), Nitrogen.i.getWindow().getHeight());
        ImGuiManager.getImplGlfw().newFrame();
        ImGui.newFrame();

        if(!a){
            startWindow();
        }
        a = true;
        renderInternal();

        ImGui.endFrame();
        ImGui.render();
        ImGuiManager.getImplGl3().renderDrawData(ImGui.getDrawData());
    }
}