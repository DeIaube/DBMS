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
import java.util.List;
import java.util.Map;

/**
 * Created by Lu on 2016/10/11.
 */
public class DeleteCommandHandle implements CommandHandle {

    @Override
    public void handle(String command) throws Exception {
        String[] commands = command.split(" ");
        String name = commands[2];
        String data = null;
        if(!commands[1].equals("from")){
            throw new SyntaxException();
        }
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

        List<Map<String, String>> deleteList = JudgeUtil.judgeEquel(dbList, command);

        if(deleteList == null){
            throw new NoParamException();
//            System.out.println("参数错误");
//            return;
        }

        dbList.getDataList().removeAll(deleteList);


        try {
            FileUtil.coverToFile(dbList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
