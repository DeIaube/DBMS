package bean;

import java.util.HashMap;
import java.util.Map;

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
    
    public void remove(String key){
        data.remove(key);
    }

}
