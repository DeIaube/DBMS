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
            if(command.contains("index")){
                if(command.contains("unique")){
                    return Command.CREATE_MAIN_INDEX;
                }
                return Command.CREATE_INDEX;
            }
            return Command.CREATE;
        }
        if(command.startsWith("delete")){
            if(command.contains("index")){
                return Command.DELETE_INDEX;
            }
            return Command.DELETE;
        }
        if(command.startsWith("update")){
            return Command.UPDATE;
        }
        if(command.startsWith("drop")){
            if(command.contains("index")){
                return Command.DELETE_INDEX;
            }
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
        if(command.startsWith("show")){
            return Command.SHOW_INDEX;
        }
        return Command.ERROR;
    }
}
