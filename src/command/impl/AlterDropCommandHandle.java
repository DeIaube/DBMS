package command.impl;

import bean.Item;
import command.BaseCommand;
import exception.DeleteKeyException;
import exception.NoParamException;
import exception.SyntaxException;
import util.FileUtil;
import util.IndexUtil;

import java.util.List;

/**
 * Created by null on 2016/10/9.
 *  alter drop Student (tail, sex)
 */
public class AlterDropCommandHandle extends BaseCommand {

    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();
        for(int i = 3; i < commands.length; i++){
            if(!dbList.getItemList().contains(commands[i])){
                throw new NoParamException();
            }
            if(commands[i].equals(dbList.getKey())){
                throw new DeleteKeyException();
            }
        }

        for(int i = 3; i < commands.length; i++){
            List<String> itemList = dbList.getItemList();
            List<Item> dataList = dbList.getDataList();
            String itemName = commands[i];
            itemList.remove(itemName);
            IndexUtil.removeIndex(dbList, itemName);
            for (Item map : dataList) {
                map.remove(itemName);
            }
        }
    }

    @Override
    public String getCommandName() {
        return commands[2];
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\,", "").split(" ");
        if(!commands[1].equals("drop")){
            throw new SyntaxException();
        }
    }

    @Override
    public void handleEnd() throws Exception {
        super.handleEnd();
        FileUtil.coverToFile(dbList);
    }
}


//
//public class AlterDropCommandHandle implements CommandHandle {
//    @Override
//    public void handle(String command) throws Exception {
//        String[] commands = command.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\,", "").split(" ");
//        if(!commands[1].equals("drop")){
//            throw new SyntaxException();
////            System.out.println("语法错误,请重试");
////            return;
//        }
//        String name = commands[2];
//        String data = null;
//        try {
//            data = FileUtil.readFromFile(name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(data == null){
//            throw new NoTableException();
////            System.out.println(name + "表不存在");
////            return;
//        }
//
//        DbList dbList = new Gson().fromJson(data, DbList.class);
//        for(int i = 3; i < commands.length; i++){
//            if(!dbList.getItemList().contains(commands[i])){
//                throw new NoParamException();
////                System.out.println("无" + commands[i] + "属性,请重试");
////                return;
//            }
//        }
//
//        for(int i = 3; i < commands.length; i++){
//            List<String> itemList = dbList.getItemList();
//            List<Map<String, String>> dataList = dbList.getDataList();
//            itemList.remove(commands[i]);
//            for (Map<String, String> map : dataList) {
//                map.remove(commands[i]);
//            }
//        }
//
//        try {
//            FileUtil.coverToFile(dbList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
