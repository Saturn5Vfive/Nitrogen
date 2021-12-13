package net.nitrogen.config.conf;

public class Custom<T> {
    private final String key;
    protected T value;

    public Custom(String v, T l){
        this.key = v;
        this.value = l;
    }

    public T getThis() {
        return value;
    }

    public void set(Object value) {
        if (value.getClass() != this.getType()) return;
        this.value = (T) value;
    }

    public String getKey() {
        return key;
    }

    public Class<?> getType() {
        return value.getClass();
    }
}