package command.impl;

import command.BaseCommand;
import exception.NoParamException;
import exception.SyntaxException;
import util.JudgeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by null on 2016/10/9.
 * select * from Student
   select * from Student where id=11
   select name,grade from Student
   select name,grade from Student where id=11 or grade=66
 */
public class SelectCommandHandle extends BaseCommand{


    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();
        List<String> keyList;
        String key = commands[1];
        if(key.equals("*")){
            keyList = dbList.getItemList();
        }else{
            keyList = new ArrayList<>();
            for (String s : key.split("\\,")) {
                if(!dbList.getItemList().contains(s)){
                    throw new NoParamException();
                }
                keyList.add(s);
            }
        }
        for (String s : keyList) {
            System.out.print(s + " ");
        }
        System.out.println();

        List<Map<String, String>> daList = JudgeUtil.judgeEquel(dbList, command);

        for (Map<String, String> map : daList) {
            for (String s : keyList) {
                System.out.print(map.get(s) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String getCommandName() {
        return commands[3];
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.split(" ");
        if(!commands[2].equals("from")){
            throw new SyntaxException();
        }
    }
}





//public class SelectCommandHandle implements CommandHandle {
//
//    @Override
//    public void handle(String command) throws Exception {
//        String[] commands = command.split(" ");
//        if(!commands[2].equals("from")){
//            throw new SyntaxException();
//        }
//
//
//        String name = commands[3];
//        String data = null;
//        try {
//            data = FileUtil.readFromFile(name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(data == null){
//            throw new NoTableException();
//        }
//        DbList dbList = new Gson().fromJson(data, DbList.class);
//        List<String> keyList;
//        String key = commands[1];
//        if(key.equals("*")){
//            keyList = dbList.getItemList();
//        }else{
//            keyList = new ArrayList<>();
//            for (String s : key.split("\\,")) {
//                if(!dbList.getItemList().contains(s)){
//                    throw new NoParamException();
//                }
//                keyList.add(s);
//            }
//        }
//        for (String s : keyList) {
//            System.out.print(s + " ");
//        }
//        System.out.println();
//
//        List<Map<String, String>> daList = JudgeUtil.judgeEquel(dbList, command);
//
//        for (Map<String, String> map : daList) {
//            for (String s : keyList) {
//                System.out.print(map.get(s) + " ");
//            }
//            System.out.println();
//        }
//    }
//}
