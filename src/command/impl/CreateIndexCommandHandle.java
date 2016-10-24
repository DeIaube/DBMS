package command.impl;

import bean.Item;
import command.BaseCommand;
import exception.ExistingIndexException;
import exception.NoParamException;
import exception.SyntaxException;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by null on 2016/10/22.
 * create index student name
 */
public class CreateIndexCommandHandle  extends BaseCommand{

    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();
        String indexName = commands[3];
        if(!dbList.getItemList().contains(indexName)){
            throw new NoParamException();
        }
        if(dbList.isIndex(indexName)){
            throw new ExistingIndexException();
        }

        List<Item> dataList = dbList.getDataList();
        TreeMap<String, List<Item>> indexMap = new TreeMap<>();
        for (Item item : dataList) {
            String key = item.get(indexName);
            List<Item> itemList = indexMap.get(key);
            if(itemList == null){
                indexMap.put(key, itemList);
            }
            itemList.add(item);
        }
        dbList.addIndex(indexName, indexMap);
    }

    @Override
    public void handleEnd() throws Exception {
        super.handleEnd();
        coverToFile();
    }

    @Override
    public String getCommandName() {
        return commands[2];
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.split(" ");
        if(commands.length != 4 || !commands[1].equals("index")){
            throw new SyntaxException();
        }
    }
}
