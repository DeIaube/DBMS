package command.impl;

import bean.DbList;
import com.google.gson.Gson;
import command.CommandHandle;
import exception.NoParamException;
import exception.NoTableException;
import exception.SyntaxException;
import util.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by null on 2016/10/9.
 */
public class AlterDropCommandHandle implements CommandHandle {
    @Override
    public void handle(String command) throws Exception {
        String[] commands = command.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\,", "").split(" ");
        if(!commands[1].equals("drop")){
            throw new SyntaxException();
//            System.out.println("语法错误,请重试");
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
        for(int i = 3; i < commands.length; i++){
            if(!dbList.getItemList().contains(commands[i])){
                throw new NoParamException();
//                System.out.println("无" + commands[i] + "属性,请重试");
//                return;
            }
        }

        for(int i = 3; i < commands.length; i++){
            List<String> itemList = dbList.getItemList();
            List<Map<String, String>> dataList = dbList.getDataList();
            itemList.remove(commands[i]);
            for (Map<String, String> map : dataList) {
                map.remove(commands[i]);
            }
        }

        try {
            FileUtil.coverToFile(dbList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
