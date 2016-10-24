package command.impl;

import bean.Item;
import command.BaseCommand;
import exception.ParamException;
import exception.SyntaxException;
import util.IndexUtil;

import java.util.List;

/**
 * Created by null on 2016/10/9.
 *  insert into Student values (哪儿里去了,13,53)
    insert into Student values (跑的特别快,76,54)
    insert into Student values (谈笑风生,71,24)

 insert into Student (name,grade) values (老王,23)
 */
public class InsertCommandHandle extends BaseCommand {


    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();
        Item map = new Item();
        String key = null;
        String value;
        if(commands[3].equals("values")){
            // 原生的key
            value = commands[4].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\,", " ");
        }else if(commands[4].equals("values")){
            key = commands[3].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\,", " ");
            value = commands[5].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\,", " ");
        }else{
            throw new SyntaxException();
        }

        if(key == null){
            List<String> myKey = dbList.getItemList();
            String[] values = value.split(" ");
            if(values.length != myKey.size()){
                throw new ParamException();
            }

            for(int i = 0; i < values.length; i++){
                map.put(myKey.get(i), values[i]);
            }

        }else{
            String[] values = value.split(" ");
            String[] keys = key.split(" ");
            if(keys.length != values.length){
                throw new ParamException();
            }
            List<String> myKey = dbList.getItemList();
            for(int i = 0; i < keys.length; i++){
                if(!myKey.contains(keys[i])){
                    throw new ParamException();
                }
                map.put(keys[i], values[i]);
            }
        }

        dbList.addData(map);
        IndexUtil.addItem(dbList, map);
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
        if(!commands[1].equals("into")){
            throw new SyntaxException();
        }
    }
}
