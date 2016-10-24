package bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by null on 2016/10/22.
 */
public class Item {
    private Map<String, String> data;

    public Item() {
        data = new HashMap<>();
    }

    public void put(String key, String value){
        data.put(key,value);
    }

    public String get(String key){
        return data.get(key);
    }

    public void remove(String key){
        data.remove(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(data, item.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    public void addAll(Item item){
        String key = null;
        String values = null;
        for (String s : item.getKeys()) {
            key = s;
            values = item.get(key);
            data.put(key,values);
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "data=" + data +
                '}';
    }

    public Set<String> getKeys(){
        return data.keySet();
    }

}
