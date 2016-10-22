package command.impl;

import command.BaseCommand;
import exception.ExistingIndexException;
import exception.NoParamException;
import exception.SyntaxException;

import java.util.List;
import java.util.Map;
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

        List<Map<String, String>> dataList = dbList.getDataList();
        TreeMap<String, Map<String, String>> indexMap = new TreeMap<>();
        for (Map<String, String> map : dataList) {
            String key = map.get(indexName);
            indexMap.put(key, map);
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
