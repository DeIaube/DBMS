package command.impl;

import bean.DbList;
import com.google.gson.Gson;
import command.CommandHandle;
import exception.NoTableException;
import exception.ParamException;
import exception.SyntaxException;
import util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by null on 2016/10/9.
 */
public class InsertCommandHandle implements CommandHandle {

    @Override
    public void handle(String command) throws Exception {
        String[] commands = command.split(" ");
        if(!commands[1].equals("into")){
            throw new SyntaxException();
//            System.out.println("语法错误，请重试1");
//            return;
        }
        String name = commands[2];
        String data = null;
        try {
            data = FileUtil.readFromFile(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(data == null){
            throw new NoTableException();
//            System.out.println(name + "表不存在");
//            return;
        }

        DbList dbList = new Gson().fromJson(data, DbList.class);
        Map<String, String> map = new HashMap<>();
        String key = null;
        String value = null;
        if(commands[3].equals("values")){
            // 原生的key
            value = commands[4].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\,", " ");
        }else if(commands[4].equals("values")){
            key = commands[3].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\,", " ");
            value = commands[5].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\,", " ");
        }else{
            throw new SyntaxException();
//            System.out.println("语法错误，请重试");
//            return;
        }

        if(key == null){
            List<String> myKey = dbList.getItemList();
            String[] values = value.split(" ");
            if(values.length != myKey.size()){
                throw new ParamException();
//                System.out.println("参数不匹配");
//                return;
            }

            for(int i = 0; i < values.length; i++){
                map.put(myKey.get(i), values[i]);
            }

        }else{
            String[] values = value.split(" ");
            String[] keys = key.split(" ");
            if(keys.length != values.length){
                throw new ParamException();
//                System.out.println("参数不匹配");
//                return;
            }
            List<String> myKey = dbList.getItemList();
            for(int i = 0; i < keys.length; i++){
                if(!myKey.contains(keys[i])){
                    throw new ParamException();
//                    System.out.println("参数不匹配");
//                    return;
                }
                map.put(keys[i], values[i]);
            }
        }

        dbList.addData(map);
        try {
            FileUtil.coverToFile(dbList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
