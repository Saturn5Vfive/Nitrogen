package net.nitrogen.feature.modules;

import net.nitrogen.config.template.Module;
import net.nitrogen.gui.clickgui.ClickGui;
import net.minecraft.client.util.math.MatrixStack;
import net.nitrogen.Nitrogen;
import net.nitrogen.config.Type;
import net.nitrogen.config.conf.*;

public class ClickGuiModule extends Module{
    public ClickGuiModule(){
        super("ClickGUI", "gooey clicks", Type.RENDER);
    }

    @Override
    public void onEnable(){
    }

    @Override
    public void onDisable(){
    }

    @Override
    public void onUpdate(){

    }

    @Override
    public void onRenderWorld(MatrixStack matrix, float partialTicks) {
        Nitrogen.i.setScreen(new ClickGui());
        this.setEnabled(false);
    }
}
