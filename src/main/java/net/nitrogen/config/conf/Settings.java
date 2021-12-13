package net.nitrogen.config.conf;

import net.minecraft.util.math.MathHelper;
import net.nitrogen.config.conf.*;

import java.util.ArrayList;
import java.util.List;


public class Settings {
    List<Custom<?>> settings = new ArrayList<>();

    private void addProxy(Custom<?> v) {
        settings.add(v);
    }

    public <T> Custom<T> add(String key, T value){
        Custom<T> custom = new Custom<>(key, value);
        addProxy(custom);
        return custom;
    }

    public Slider add(String key, double value, double min, double max, int p){
        Slider slider = new Slider(key, MathHelper.clamp(value, min, max), min, max, p);
        addProxy(slider);
        return slider;
    }

    public Bool add(String key, boolean defa){
        Bool bool = new Bool(key, defa);
        addProxy(bool);
        return bool;
    }

    public Combo add(String key, String value, String... possible) {
        Combo ev = new Combo(key, value, possible);
        addProxy(ev);
        return ev;
    }

    public Custom<?> get(String key) {
        for (Custom<?> Custom : settings) {
            if (Custom.getKey().equalsIgnoreCase(key)) return Custom;
        }
        return null;
    }

    public List<Custom<?>> returnThis() {
        return settings;
    }
}