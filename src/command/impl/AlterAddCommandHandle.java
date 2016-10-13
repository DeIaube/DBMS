package command.impl;

import bean.DbList;
import com.google.gson.Gson;
import command.CommandHandle;
import exception.ExistingParametersException;
import exception.NoParamException;
import exception.NoTableException;
import exception.SyntaxException;
import util.FileUtil;

import java.io.IOException;

/**
 * Created by null on 2016/10/9.
 */
public class AlterAddCommandHandle implements CommandHandle {
    @Override
    public void handle(String command) throws Exception {
        String[] commands = command.replaceAll("\\,", "").replaceAll("\\(", "").replaceAll("\\)", "").split(" ");
        if(!commands[1].equals("add")){
            throw new SyntaxException();
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
//            System.out.println(name + "表不存在, 请重试");
//            return;
        }

        DbList dbList = new Gson().fromJson(data, DbList.class);


        for(int i = 3; i < commands.length; i+=2){
            if(dbList.getItemList().contains(commands[i])){
                throw new ExistingParametersException();
//                System.out.println(commands[i]  + "已存在,请重试");
//                return;
            }
            if(!commands[i + 1].equals("string") && !commands[i + 1].equals("integer")){
                throw new NoParamException();
//                System.out.println("不支持的属性,请重试");
//                return;
            }
        }

        for(int i = 3; i < commands.length; i+=2){
            dbList.addItem(commands[i], commands[i + 1]);
            dbList.getItemList().add(commands[i]);
        }

        try {
            FileUtil.coverToFile(dbList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
