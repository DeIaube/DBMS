package bean;

import java.util.*;

/**
 * Created by null on 2016/10/9.
 */
public class DbList {
    private LinkedHashMap<String, String> itemMap;
    private String name;

    private List<Item> dataList;

    private String key = null;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    //属性名称列表
    private List<String> itemList;

    private List<String> indexNameList;

    private TreeMap<String, Item> mainIndex;

    private Map<String, TreeMap<String, List<Item>>> index;

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

    public void addIndex(String indexName, TreeMap<String, List<Item>> indexMap){
        indexNameList.add(indexName);
        index.put(indexName, indexMap);
    }

    public String getMainIndexName() {
        return mainIndexName;
    }

    public TreeMap<String, Item> getMainIndex() {
        return mainIndex;
    }

    public void setMainIndex(TreeMap<String, Item> mainIndex) {
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

    public Map<String, TreeMap<String, List<Item>>> getIndex() {
        return index;
    }

    public void removeIndex(String indeName){
        indexNameList.remove(indeName);
        index.remove(indeName);
    }



    public List<Item> getDataList() {
        return dataList;
    }

    public void setDataList(List<Item> dataList) {
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

    public void addData(Item item){
        dataList.add(item);
    }
}
