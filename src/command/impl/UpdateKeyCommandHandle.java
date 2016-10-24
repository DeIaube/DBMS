package command.impl;

import command.BaseCommand;
import exception.NoParamException;
import exception.SyntaxException;

/**
 * Created by null on 2016/10/23.
 * update test key name
 */
public class UpdateKeyCommandHandle extends BaseCommand {
    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();
        String key = commands[3];
        if(!dbList.getItemList().contains(key)){
            throw new NoParamException();
        }
        dbList.setKey(key);
        System.out.println(dbList.getKey());
    }

    @Override
    public void handleEnd() throws Exception {
        super.handleEnd();
        coverToFile();
    }

    @Override
    public String getCommandName() {
        return commands[1];
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.split(" ");
        if(!commands[2].equals("key")){
            throw new SyntaxException();
        }
    }
}
