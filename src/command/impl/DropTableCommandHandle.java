package command.impl;

import command.CommandHandle;
import exception.NoTableException;
import exception.SyntaxException;
import util.FileUtil;

/**
 * Created by null on 2016/10/9.
 */
public class DropTableCommandHandle implements CommandHandle {

    @Override
    public void handle(String command) throws Exception {
        String[] commands = command.split(" ");
        if(!commands[1].equals("table")){
            throw new SyntaxException();
//            System.out.println("语法错误,请重试");
//            return;
        }
        String name = commands[2];
        if(!FileUtil.deleteFile(name)){
            throw new NoTableException();
//            System.out.println(name + "表不存在,请重试");
        }
    }
}
