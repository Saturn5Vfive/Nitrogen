package net.nitrogen.gui.clickgui;

import imgui.ImGui;
import net.nitrogen.config.Modules;
import net.nitrogen.config.Type;
import net.nitrogen.gui.imgui.ProxyScreen;
import net.nitrogen.config.template.Module;

public class ClickGui extends ProxyScreen {

    @Override
    protected void startWindow() {
        
    }

    @Override
    protected void renderInternal() {
        for(Type t : Type.values()){
            ImGui.setNextWindowSizeConstraints(200, 500, 400, 9000);
            ImGui.begin(t.getName());
            for(Module m : Modules.getModules()){
                if(m.getType().equals(t)){
                    if(ImGui.button(m.renderName(), -1, 30)){
                        m.toggle();
                    }
                }
            }
            ImGui.end();
        }
    }
}
