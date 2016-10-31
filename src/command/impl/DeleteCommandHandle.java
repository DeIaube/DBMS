package command.impl;

import bean.Item;
import command.BaseCommand;
import exception.NoParamException;
import exception.SyntaxException;
import util.IndexUtil;
import util.judge.JudgeUtil;

import java.util.List;

/**
 * Created by Lu on 2016/10/11.
 *  delete from Student where id=23 and grade=23
    delete from Student
 */
public class DeleteCommandHandle extends BaseCommand {


    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();
        List<Item> deleteList = JudgeUtil.judgeEquel(dbList, command);
        if(deleteList == null){
            throw new NoParamException();
        }

        dbList.getDataList().removeAll(deleteList);

        for (Item item : deleteList) {
            IndexUtil.removeItem(dbList, item);
        }
    }

    @Override
    public String getCommandName() {
        return commands[2];
    }

    @Override
    public void handleEnd() throws Exception {
        super.handleEnd();
        coverToFile();
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.split(" ");
        if(!commands[1].equals("from")){
            throw new SyntaxException();
        }
    }
}


//public class DeleteCommandHandle implements CommandHandle {
//
//    @Override
//    public void handle(String command) throws Exception {
//        String[] commands = command.split(" ");
//        String name = commands[2];
//        String data = null;
//        if(!commands[1].equals("from")){
//            throw new SyntaxException();
//        }
//        try {
//            data = FileUtil.readFromFile(name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(data == null){
//            throw new NoTableException();
//        }
//
//        DbList dbList = new Gson().fromJson(data, DbList.class);
//
//        List<Map<String, String>> deleteList = JudgeUtil.judgeEquel(dbList, command);
//
//        if(deleteList == null){
//            throw new NoParamException();
//        }
//
//        dbList.getDataList().removeAll(deleteList);
//
//
//        try {
//            FileUtil.coverToFile(dbList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
