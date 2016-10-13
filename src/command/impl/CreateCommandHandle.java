package command.impl;

import bean.DbList;
import command.CommandHandle;
import exception.ExistingTableException;
import exception.NoParamException;
import exception.SyntaxException;
import util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by null on 2016/10/9.
 */
public class CreateCommandHandle implements CommandHandle {

// create table fuch(hehe string, fuck string)
    @Override
    public void handle(String command) throws Exception {
        // 预先操作
        String[] commands = command.split(" ");
        if(!commands[1].equals("table")){
            throw new SyntaxException();
//            System.out.println("语法错误，请重试");
//            return;
        }
        String tableName = commands[2].substring(0, commands[2].indexOf("("));
        List<String> items = new ArrayList<>();
        String item = commands[2].substring(commands[2].indexOf("(") + 1, commands[2].length());
        items.add(item);
        for(int i = 3; i < commands.length; i++){
            item = commands[i].replaceAll(",", "").replaceAll("\\)", "");
            items.add(item);
        }

        // 获取到了表名与各项数据

        DbList dbList = new DbList();
        List<String> itemList = new ArrayList<>();
        dbList.setName(tableName);
        for(int i = 0; i < items.size(); i+=2){
            String shuxing = items.get(i + 1);
            if(shuxing.equals("string") || shuxing.equals("integer")){
                dbList.addItem(items.get(i), shuxing);
                itemList.add(items.get(i));
            }else{
                throw new NoParamException();
//                System.out.println("属性定义错误，请重试");
//                return;
            }
        }
        dbList.setItemList(itemList);
        try {
            if(!FileUtil.writeToFile(dbList)){
                throw new ExistingTableException();
//                System.out.println("此表已经存在");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
