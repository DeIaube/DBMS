package util;

import bean.DbList;
import bean.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by null on 2016/10/22.
 * 关于索引的增加 删除 与修改
 */
public class IndexUtil {
    public static void addItem(DbList dbList, Item item){
        Map<String, TreeMap<String, List<Item>>> index = dbList.getIndex();
        if(dbList.hasMainIndex()){
            //添加主索引
            String key = item.get(dbList.getMainIndexName());
            dbList.getMainIndex().put(key, item);
        }

        for (String indexName : dbList.getIndexNameList()) {
            TreeMap<String, List<Item>> stringListTreeMap = index.get(indexName);
            List<Item> itemList = stringListTreeMap.get(item.get(indexName));
            if(itemList == null){
                itemList = new ArrayList<>();
                stringListTreeMap.put(item.get(indexName), itemList);
            }
            itemList.add(item);
        }
    }

    public static void removeItem(DbList dbList, Item item){
        Map<String, TreeMap<String, List<Item>>> index = dbList.getIndex();
        if(dbList.hasMainIndex()){
            String key = item.get(dbList.getMainIndexName());
            dbList.getMainIndex().remove(key);
        }

        for (String indexName : dbList.getIndexNameList()) {
            TreeMap<String, List<Item>> stringListTreeMap = index.get(indexName);
            List<Item> itemList = stringListTreeMap.get(item.get(indexName));
            if(itemList != null){
                stringListTreeMap.remove(item.get(indexName));
            }
        }
    }

    public static void removeIndex(DbList dbList, String itemName){
        if(dbList.getMainIndexName()!=null && dbList.getMainIndexName().equals(itemName)){
            dbList.removeMainIndex();
        }else if(dbList.getIndexNameList().contains(itemName)){
            dbList.removeIndex(itemName);
        }
    }
}
