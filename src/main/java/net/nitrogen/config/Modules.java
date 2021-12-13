package net.nitrogen.config;

import java.util.ArrayList;
import java.util.List;
import net.nitrogen.config.template.Module;
import net.nitrogen.feature.modules.*;

public class Modules {
    static List<Module> mods = new ArrayList<>();

    static {
        mods.add(new ClickGuiModule());
    }

    public static List<Module> getModules(){
        return mods;
    }

    public static Module find(String n){
        for (Module module : mods) {
            if (module.name().equalsIgnoreCase(n)) return module;
        }
        return null;
    }
}
