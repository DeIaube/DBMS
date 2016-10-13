package analysis;

import config.Command;

/**
 * Created by null on 2016/10/9.
 */
public class CommandParser {
    public static Command parser(String command){
        if(command.startsWith("insert")){
            return Command.INSERT;
        }
        if(command.startsWith("create")){
            if(command.contains("view")){
                return Command.CREATE_VIEW;
            }
            return Command.CREATE;
        }
        if(command.startsWith("delete")){
            return Command.DELETE;
        }
        if(command.startsWith("update")){
            return Command.UPDATE;
        }
        if(command.startsWith("drop")){
            return Command.DROP_TABLE;
        }
        if(command.startsWith("select")){
            return Command.SELECT;
        }
        if(command.startsWith("alter")){
            if(command.contains("add")){
                return Command.ALTER_ADD;
            }
            if(command.contains("drop")){
                return Command.ALTER_DROP;
            }
            return Command.ERROR;
        }
        return Command.ERROR;
    }
}
