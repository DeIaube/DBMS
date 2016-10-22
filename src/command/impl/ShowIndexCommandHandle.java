package command.impl;

import command.BaseCommand;
import exception.SyntaxException;

import java.util.List;

/**
 * Created by null on 2016/10/22.
 *  show index student
 */
public class ShowIndexCommandHandle extends BaseCommand {
    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();
        System.out.println(tableName + "表索引如下");
        if(dbList.hasMainIndex()){
            System.out.println("主索引:" + dbList.getMainIndexName());
        }else{
            System.out.println("主索引:无");
        }

        List<String> indexNameList = dbList.getIndexNameList();

        System.out.println("索引:" + (indexNameList.isEmpty() ? "无" : indexNameList.toString()));
    }

    @Override
    public String getCommandName() {
        return commands[2];
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.split(" ");
        if(commands.length!=3 || !commands[1].equals("index")){
            throw new SyntaxException();
        }
    }
}
