package command.impl;

import bean.DbList;
import command.BaseCommand;
import exception.NoParamException;
import exception.SyntaxException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by null on 2016/10/9.
 * create table Student(name string, id integer, grade integer)
 *  create table test(id string key, name string)
 */
public class CreateCommandHandle extends BaseCommand {

    @Override
    public void handleCommand(String command) throws Exception {
        // 预先操作
        List<String> items = new ArrayList<>();
        String item = commands[2].substring(commands[2].indexOf("(") + 1, commands[2].length());
        items.add(item);
        for(int i = 3; i < commands.length; i++){
            item = commands[i].replaceAll(",", "").replaceAll("\\)", "");
            items.add(item);
        }

        // 获取到了表名与各项数据

        dbList = new DbList();
        List<String> itemList = new ArrayList<>();
        dbList.setName(tableName);
        for(int i = 0; i < items.size(); i+=2){
            String key = items.get(i);
            String values = items.get(i + 1);

            if(i+2<items.size() && items.get(i+2).equals("key")){
                i++;
                dbList.setKey(key);
            }
            if(values.equals("string") || values.equals("integer")){
                dbList.addItem(key, values);
                itemList.add(key);
            }else{
                throw new NoParamException();
            }
        }
        dbList.setItemList(itemList);
    }

    @Override
    public void handleEnd() throws Exception {
        super.handleEnd();
        writeToFile();
    }

    @Override
    public String getCommandName() {
        return commands[2].substring(0, commands[2].indexOf("("));
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.split(" ");
        if(!commands[1].equals("table")){
            throw new SyntaxException();
        }
    }
}

//
//public class CreateCommandHandle implements CommandHandle {
//
//    @Override
//    public void handle(String command) throws Exception {
//        // 预先操作
//        String[] commands = command.split(" ");
//        if(!commands[1].equals("table")){
//            throw new SyntaxException();
//        }
//        String tableName = commands[2].substring(0, commands[2].indexOf("("));
//        List<String> items = new ArrayList<>();
//        String item = commands[2].substring(commands[2].indexOf("(") + 1, commands[2].length());
//        items.add(item);
//        for(int i = 3; i < commands.length; i++){
//            item = commands[i].replaceAll(",", "").replaceAll("\\)", "");
//            items.add(item);
//        }
//
//        // 获取到了表名与各项数据
//
//        DbList dbList = new DbList();
//        List<String> itemList = new ArrayList<>();
//        dbList.setName(tableName);
//        for(int i = 0; i < items.size(); i+=2){
//            String shuxing = items.get(i + 1);
//            if(shuxing.equals("string") || shuxing.equals("integer")){
//                dbList.addItem(items.get(i), shuxing);
//                itemList.add(items.get(i));
//            }else{
//                throw new NoParamException();
//            }
//        }
//        dbList.setItemList(itemList);
//        try {
//            if(!FileUtil.writeToFile(dbList)){
//                throw new ExistingTableException();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
