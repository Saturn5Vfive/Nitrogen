package net.nitrogen.config.template;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.util.math.MatrixStack;
import net.nitrogen.config.Type;
import net.nitrogen.config.conf.Settings;

public abstract class Module {
    private final String name;
    private final String description;
    private final Type type;  
    public Settings settings;
    private boolean enabled = false;

    public Module(String name, String description, Type type){
        this.name = name;
        this.description = description;
        this.type = type;
        this.settings = new Settings();
        this.settings.add("Keybind", -1);
    }

    public Type getType(){
        return this.type;
    }

    public String name(){
        return name;
    }

    public String renderName(){
        return name;
    }

    public List<String> identifiers(){
        return new ArrayList<String>();
    }

    public String description(){
        return this.description;
    }

    public abstract void onUpdate();
    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void onRenderWorld(MatrixStack matrix, float partialTicks);


    public void toggle(){
        setEnabled(!enabled);
    }

    public boolean enabled(){
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (this.enabled) this.onEnable();
        else this.onDisable();
    }
}   
