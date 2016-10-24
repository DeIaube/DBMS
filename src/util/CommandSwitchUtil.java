package util;

import config.Command;

/**
 * Created by null on 2016/10/24.
 */
public class CommandSwitchUtil {
    public static Command switchFromString(String data){
        switch (data){
            case "create":
                return Command.CREATE;
            case "insert":
                return Command.INSERT;
            case "delete":
                return Command.DELETE;
            case "update":
                return Command.UPDATE;
            case "select":
                return Command.SELECT;
            case "alter_drop":
                return Command.ALTER_DROP;
            case "alter_add":
                return Command.ALTER_ADD;
            case "drop_table":
                return Command.DROP_TABLE;
            case "create_index":
                return Command.CREATE_INDEX;
            case "delete_index":
                return Command.DELETE_INDEX;
            case "create_main_index":
                return Command.CREATE_MAIN_INDEX;
            case "show_index":
                return Command.SHOW_INDEX;
            case "update_key":
                return Command.UPDATE_KEY;
            case "create_user":
                return Command.CREATE_USER;
            default:
                return Command.ERROR;
        }
    }
}
