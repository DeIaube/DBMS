package command.impl;

import command.BaseCommand;
import exception.NoIndexException;
import exception.NoParamException;
import exception.SyntaxException;

/**
 * Created by null on 2016/10/22.
 *  delete index student id
 */
public class DeleteIndexCommandHandle extends BaseCommand {

    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();

        String indexName = commands[3];
        if(!dbList.getItemList().contains(indexName)){
            throw new NoParamException();
        }
        if(dbList.getMainIndexName() != null && dbList.getMainIndexName().equals(indexName)){
            dbList.removeMainIndex();
        }else if(dbList.getIndexNameList().contains(indexName)){
            dbList.removeIndex(indexName);
        }else{
            throw new NoIndexException();
        }

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
