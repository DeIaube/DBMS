package command.impl;

import bean.DbList;
import bean.Item;
import command.BaseCommand;
import exception.NoParamException;
import exception.NoTableException;
import exception.SyntaxException;
import util.JudgeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by null on 2016/10/9.
 * select * from Student
   select * from Student where id=11
   select name,grade from Student
   select name,grade from Student where id=11 or grade=66

   select * from Student
   select * from student,transcript,subject where student.id=transcript.id and transcript.subject=subject.subject
   select * from student,transcript,subject where student.id=transcript.id and transcript.subject=subject.subject and id=1
 */
public class SelectCommandHandle extends BaseCommand{
//    2 5
//    3 5 7
    String rawCommand;
    String finalCommand;
    @Override
    public void handleCommand(String command) throws Exception {
        rawCommand = command;
        List<String> keyList = null;
        if(tableName.contains(",")){
            //多表查询
            dbList = multiTableQuery(tableName);
        }else{
            initDbList();
            finalCommand = rawCommand;
        }

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
            if(dbList.getKey()!= null && dbList.getKey().equals(s)){
                System.out.print(s + "(key) ");
            }else{
                System.out.print(s + " ");
            }
        }

        System.out.println();

        List<Item> daList = JudgeUtil.judgeEquel(dbList, finalCommand);

        for (Item map : daList) {
            for (String s : keyList) {
                System.out.print(map.get(s) + " ");
            }
            System.out.println();
        }
    }

    /**
     * 多表查询部分
     * @param tableName
     */
    private DbList multiTableQuery(String tableName) throws NoTableException {
        String[] tableNames = tableName.split("\\,");
        List<String> duobiaotiaojian = new ArrayList<>();
        int index = 5;
        for(int i = 0; i < tableNames.length - 1; i++){
            duobiaotiaojian.add(commands[index]);
            index += 2;
        }

        String first = duobiaotiaojian.get(0);
        String last = duobiaotiaojian.get(duobiaotiaojian.size()-1);
        if(index <= commands.length){
            finalCommand = rawCommand.replace(rawCommand.substring(rawCommand.indexOf(first)-1, rawCommand.indexOf(last) + last.length() + 4),"");
        }else{
            finalCommand = rawCommand.replace(rawCommand.substring(rawCommand.indexOf(first)-1, rawCommand.indexOf(last) + last.length()),"");
            finalCommand = finalCommand.replaceAll("where","");
        }

        LinkedList<DbList> dbListLinkedList = new LinkedList<>();
        for (String name : tableNames) {
            DbList dbList = loadDbList(name);
            dbListLinkedList.add(dbList);
        }

        for (String tiaojian : duobiaotiaojian) {
            String temp = tiaojian.split("=")[0];
            String key = temp.substring(temp.indexOf(".") + 1, temp.length());
//            System.out.println(key);
            DbList db1 = dbListLinkedList.removeFirst();
            DbList db2 = dbListLinkedList.removeFirst();
            DbList result = intersection(db1, db2, key);
            dbListLinkedList.addFirst(result);
        }

        DbList result = dbListLinkedList.removeFirst();

        List<String> itemList = new ArrayList<>();
        if(result.getDataList().get(0) != null){
            for (String s : result.getDataList().get(0).getKeys()) {
                itemList.add(s);
            }
        }
        result.setItemList(itemList);
        return result;

    }

    private DbList intersection(DbList db1, DbList db2, String key) {
        DbList result = new DbList();
        List<Item> d1 = db1.getDataList();
        List<Item> d2 = db2.getDataList();
        for (Item i1 : d1) {
            String values = i1.get(key);
            for (Item i2 : d2) {
                if(i2.get(key).equals(values)){
                    Item fuck = new Item();
                    fuck.addAll(i1);
                    fuck.addAll(i2);
                    result.addData(fuck);
                }
            }
        }
        return result;
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
