package net.nitrogen.config.conf;

import net.minecraft.util.math.MathHelper;

public class Slider extends Custom<Double>{
    double min;
    double max;
    int prec;

    public Slider(String key, double value, double min, double max, int p){
        super(key, value);
        this.min = min;
        this.max = max;
        this.prec = MathHelper.clamp(p, 0, 10);
    }

    @Override
    public void set(Object value) {
        if (!(value instanceof Double)) return;
        this.value = MathHelper.clamp((double) value, min, max);
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}