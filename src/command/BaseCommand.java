package command;

import bean.DbList;
import com.google.gson.Gson;
import exception.ExistingTableException;
import exception.NoTableException;
import util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by null on 2016/10/21.
 */
public abstract class BaseCommand implements CommandHandle {

    protected DbList dbList = null;
    protected String tableName = null;
    protected String[] commands = null;
    protected List<String> cacheCommand;

    public BaseCommand() {
        cacheCommand = new ArrayList<>();
    }

    @Override
    public void handle(String command) throws Exception {
        checkCommand(command);
        tableName = getCommandName();
        handleCommand(command);
        handleEnd();
    }

    public abstract void handleCommand(String command) throws Exception;

    public abstract String getCommandName();

    public abstract void checkCommand(String command) throws Exception;

    protected void initDbList() throws NoTableException {
        dbList = loadDbList(tableName);
    }

    protected DbList loadDbList(String table) throws NoTableException {
        DbList db;
        String data = null;
        try {
            data = FileUtil.readFromFile(table);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(data == null){
            throw new NoTableException();
        }
        db = new Gson().fromJson(data, DbList.class);
        return db;
    }


    public void handleEnd() throws Exception{
        if(dbList == null){
            return;
        }
    }

    protected void coverToFile() {
        try {
            FileUtil.coverToFile(dbList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void deleteFilr() throws NoTableException {
        if(!FileUtil.deleteFile(tableName)){
            throw new NoTableException();
        }
    }

    protected void writeToFile() throws Exception {
        if(!FileUtil.writeToFile(dbList)){
            throw new ExistingTableException();
        }
    }

}
