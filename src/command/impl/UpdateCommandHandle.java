package command.impl;

import bean.DbList;
import com.google.gson.Gson;
import command.CommandHandle;
import exception.NoParamException;
import exception.NoTableException;
import util.FileUtil;
import util.JudgeUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by null on 2016/10/9.
 */
public class UpdateCommandHandle implements CommandHandle {
    @Override
    public void handle(String command) throws Exception {
        String[] commands = command.split(" ");
        String name = commands[1];
        String updateData = commands[2];
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
        List<Map<String, String>> updateList = JudgeUtil.judgeEquel(dbList, command);


//        String[] conditions = condition.split("=");
//        List<Map<String, String>> updateList = new ArrayList<>();
//        for (Map<String, String> map : dbList.getDataList()) {
//            if(map.get(conditions[0]) != null && map.get(conditions[0]).equals(conditions[1])){
//                updateList.add(map);
//            }
//        }
//
        String[] updataDatas = updateData.split("\\,");
        for (String updataData : updataDatas) {
            String[] split = updataData.split("=");
            if(!dbList.getItemList().contains(split[0])){
                throw new NoParamException();
//                System.out.println("无" + split[0] + "参数");
//                return;
            }
        }

        for (String updataData : updataDatas) {
            String[] split = updataData.split("=");
            for (Map<String, String> map : updateList) {
                map.put(split[0], split[1]);
            }
        }

        try {
            FileUtil.coverToFile(dbList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}