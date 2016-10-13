package util;

import command.CommandHandle;
import command.impl.*;
import config.Command;

/**
 * Created by null on 2016/10/9.
 */
public class CommandHandleHolder {
    private static CreateCommandHandle createCommandHandle;
    private static InsertCommandHandle insertCommandHandle;
    private static SelectCommandHandle selectCommandHandle;
    private static DropTableCommandHandle dropTableCommandHandle;
    private static AlterAddCommandHandle alterAddCommandHandle;
    private static AlterDropCommandHandle alterDropCommandHandle;
    private static UpdateCommandHandle updateCommandHandle;
    private static DeleteCommandHandle deleteCommandHandle;
    private static CreateViewCommandHandle createViewCommandHandle;


    static {
        createCommandHandle = new CreateCommandHandle();
        insertCommandHandle = new InsertCommandHandle();
        selectCommandHandle = new SelectCommandHandle();
        dropTableCommandHandle = new DropTableCommandHandle();
        alterAddCommandHandle = new AlterAddCommandHandle();
        alterDropCommandHandle = new AlterDropCommandHandle();
        updateCommandHandle = new UpdateCommandHandle();
        deleteCommandHandle = new DeleteCommandHandle();
        createViewCommandHandle = new CreateViewCommandHandle();
    }


    public static CommandHandle getHandle(Command command){
        switch (command){
            case CREATE:
                return createCommandHandle;
            case INSERT:
                return insertCommandHandle;
            case SELECT:
                return selectCommandHandle;
            case DROP_TABLE:
                return dropTableCommandHandle;
            case ALTER_ADD:
                return alterAddCommandHandle;
            case ALTER_DROP:
                return alterDropCommandHandle;
            case UPDATE:
                return updateCommandHandle;
            case DELETE:
                return deleteCommandHandle;
            case CREATE_VIEW:
                return createViewCommandHandle;

        }
        return createCommandHandle;
    }

    public static CreateCommandHandle getCreateCommandHandle() {
        return createCommandHandle;
    }

    public static InsertCommandHandle getInsertCommandHandle() {
        return insertCommandHandle;
    }

    public static SelectCommandHandle getSelectCommandHandle() {
        return selectCommandHandle;
    }

    public static DropTableCommandHandle getDropTableCommandHandle() {
        return dropTableCommandHandle;
    }

    public static AlterAddCommandHandle getAlterAddCommandHandle() {
        return alterAddCommandHandle;
    }

    public static AlterDropCommandHandle getAlterDropCommandHandle() {
        return alterDropCommandHandle;
    }

    public static UpdateCommandHandle getUpdateCommandHandle() {
        return updateCommandHandle;
    }

    public static DeleteCommandHandle getDeleteCommandHandle() {
        return deleteCommandHandle;
    }

    public static CreateViewCommandHandle getCreateViewCommandHandle() {
        return createViewCommandHandle;
    }
}
