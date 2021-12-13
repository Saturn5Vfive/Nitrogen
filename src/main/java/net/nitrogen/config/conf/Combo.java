package net.nitrogen.config.conf;

import com.google.common.collect.Lists;
import java.util.List;

public class Combo extends Custom<String>{
    List<String> possible;

    public Combo(String key, String value, String... values) {
        super(key, value);
        this.possible = Lists.newArrayList(values);
    }

    public int getIndex() {
        return possible.indexOf(this.value);
    }

    @Override
    public void set(Object value) {
        if (!(value instanceof String)) return;
        if (!possible.contains(value)) return;
        this.value = (String) value;
    }

    public void setIndex(int index){
        this.set(possible.get(index));
    }

    public void cycle() {
        int next = getIndex() + 1;
        if (next >= possible.size()) next = 0;
        this.set(possible.get(next));
    }

    public List<String> values() {
        return possible;
    }
}