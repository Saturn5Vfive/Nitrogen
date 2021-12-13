package net.nitrogen.config.conf;

public class Bool extends Custom<Boolean>{
    public Bool(String key, boolean value) {
        super(key, value);
    }

    @Override
    public void set(Object value) {
        if (!(value instanceof Boolean)) return;
        this.value = (Boolean) value;
    }
}
