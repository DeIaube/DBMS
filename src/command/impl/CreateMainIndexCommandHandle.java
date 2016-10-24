package command.impl;

import bean.Item;
import command.BaseCommand;
import exception.ExistingMainIndexException;
import exception.NoParamException;
import exception.SyntaxException;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by null on 2016/10/22.
 *  create unique index student id
 */
public class CreateMainIndexCommandHandle extends BaseCommand {

    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();
        if(dbList.hasMainIndex()){
            throw new ExistingMainIndexException();
        }

        String mainIndexName = commands[4];
        if(!dbList.getItemList().contains(mainIndexName)){
            throw new NoParamException();
        }

        List<Item> dataList = dbList.getDataList();
        TreeMap<String, Item> mainIndexData = new TreeMap<>();
        for (Item map : dataList) {
            String key = map.get(mainIndexName);
            mainIndexData.put(key, map);
        }

        dbList.setMainIndexName(mainIndexName);
        dbList.setMainIndex(mainIndexData);
    }


    @Override
    public void handleEnd() throws Exception {
        super.handleEnd();
        coverToFile();
    }

    @Override
    public String getCommandName() {
        return commands[3];
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.split(" ");
        if(commands.length != 5 || !commands[1].equals("unique") || !commands[2].equals("index")){
            throw new SyntaxException();
        }
    }
}
