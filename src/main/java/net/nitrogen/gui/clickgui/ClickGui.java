package net.nitrogen.gui.clickgui;

import imgui.ImGui;
import net.nitrogen.config.Modules;
import net.nitrogen.config.Type;
import net.nitrogen.config.conf.*;
import net.nitrogen.gui.imgui.ProxyScreen;
import net.nitrogen.utils.ChatUtils;
import net.nitrogen.config.template.Module;

public class ClickGui extends ProxyScreen {
    Module currentModule = null;


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
                    ImGui.button(m.renderName(), -1, 30);
                    if(ImGui.isItemClicked(0)){
                        m.toggle();
                    }
                    if(ImGui.isItemClicked(1)){
                        currentModule = m;
                    }
                }
            }
            ImGui.end();
        }
        ImGui.setNextWindowSizeConstraints(300, 300, 2000, 2000);
        if(currentModule != null){
            ImGui.begin("Config");
            for(Custom<?> value : currentModule.settings.returnThis()){
                if(value.getKey().equals("Keybind")){
                    //gonna leave this blank for now
                }else if(value instanceof Bool){
                    Bool active = ((Bool) value);
                    boolean setter = active.getThis();
                    ImGui.checkbox(value.getKey(), setter);
                    value.set(setter);
                }else if(value instanceof Slider){
                    Slider s = ((Slider)value);
                    int[] slider = new int[]{(int)Math.round(s.getThis())};
                    ImGui.sliderInt(value.getKey(), slider, (int)s.getMin(), (int)s.getMax());
                    value.set(slider[0]);
                }else if(value instanceof Combo){
                    Combo c = ((Combo)value);
                    ImGui.beginListBox(c.getKey(), 160, -1);
                    for(String val : c.values()){
                        if(ImGui.selectable(val, c.getThis().equals(val))){
                            value.set(val);
                        }
                    }
                    ImGui.endListBox();
                }else{

                }
            }
            ImGui.end();
        }
    }
}
