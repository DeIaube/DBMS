package bean;

import java.util.*;

/**
 * Created by null on 2016/10/9.
 */
public class DbList {
    private LinkedHashMap<String, String> itemMap;
    private String name;

    private List<Map<String, String>> dataList;

    //属性列表
    private List<String> itemList;

    public void setItemMap(LinkedHashMap<String, String> itemMap) {
        this.itemMap = itemMap;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public DbList() {
        itemMap = new LinkedHashMap<>();
        dataList = new ArrayList<>();
        itemList = new ArrayList<>();

    }

    public Map<String, String> getItemMap() {
        return itemMap;
    }


    public List<Map<String, String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, String>> dataList) {
        this.dataList = dataList;
    }

    public void addItem(String name, String item){
        itemMap.put(name, item);
    }

    public void removeItem(String name){
        // // TODO: 2016/10/9 移除属性
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addData(Map<String, String> dataMap){
        dataList.add(dataMap);
    }
}
