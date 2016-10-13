package command.impl;

import bean.DbList;
import com.google.gson.Gson;
import command.CommandHandle;
import exception.NoParamException;
import exception.NoTableException;
import exception.SyntaxException;
import util.FileUtil;
import util.JudgeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by null on 2016/10/9.
 */
public class SelectCommandHandle implements CommandHandle {

    @Override
    public void handle(String command) throws Exception {
        String[] commands = command.split(" ");
        if(!commands[2].equals("from")){
            throw new SyntaxException();
//            System.out.println("语法错误,请重试");
        }


        String name = commands[3];
        String data = null;
        try {
            data = FileUtil.readFromFile(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(data == null){
            throw new NoTableException();
//            System.out.println(name + "不存在,请重试");
        }
        DbList dbList = new Gson().fromJson(data, DbList.class);
        List<String> keyList = null;
        String key = commands[1];
        if(key.equals("*")){
             keyList = dbList.getItemList();
        }else{
            keyList = new ArrayList<>();
            for (String s : key.split("\\,")) {
                if(!dbList.getItemList().contains(s)){
                    throw new NoParamException();
//                    System.out.println("无" + s + "参数,请重试");
//                    return;
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
}


