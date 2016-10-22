package bean;

import java.util.*;

/**
 * Created by null on 2016/10/9.
 */
public class DbList {
    private LinkedHashMap<String, String> itemMap;
    private String name;

    private List<Map<String, String>> dataList;

    //属性名称列表
    private List<String> itemList;

    private List<String> indexNameList;

    private TreeMap<String, Map<String, String>> mainIndex;

    private Map<String, TreeMap<String, Map<String, String>>> index;

    private String mainIndexName;

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
        indexNameList = new ArrayList<>();
        index = new HashMap<>();
    }

    public boolean hasMainIndex(){
        return mainIndexName != null;
    }

    public List<String> getIndexNameList() {
        return indexNameList;
    }

    public boolean isIndex(String name){
        if(mainIndexName != null && mainIndexName.equals(name)){
            return true;
        }

        return indexNameList.contains(name);
    }

    public void addIndex(String indexName, TreeMap<String, Map<String, String>> indexMap){
        indexNameList.add(indexName);
        index.put(indexName, indexMap);
    }

    public String getMainIndexName() {
        return mainIndexName;
    }

    public TreeMap<String, Map<String, String>> getMainIndex() {
        return mainIndex;
    }

    public void setMainIndex(TreeMap<String, Map<String, String>> mainIndex) {
        this.mainIndex = mainIndex;
    }

    public void setMainIndexName(String mainIndexName) {
        this.mainIndexName = mainIndexName;
    }

    public Map<String, String> getItemMap() {
        return itemMap;
    }

    public void removeMainIndex(){
        mainIndexName = null;
        mainIndex = null;
    }

    public void removeIndex(String indeName){
        indexNameList.remove(indeName);
        index.remove(indeName);
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
