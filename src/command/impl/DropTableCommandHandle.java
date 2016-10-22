package command.impl;

import command.BaseCommand;
import exception.SyntaxException;

/**
 * Created by null on 2016/10/9.
 *  drop table Student
 */
public class DropTableCommandHandle extends BaseCommand {


    @Override
    public void handleCommand(String command) throws Exception {

    }

    @Override
    public void handleEnd() throws Exception {
        super.handleEnd();
        deleteFilr();
    }

    @Override
    public String getCommandName() {
        return commands[2];
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.split(" ");
        if(!commands[1].equals("table")){
            throw new SyntaxException();
        }
    }
}



//public class DropTableCommandHandle implements CommandHandle {
//
//    @Override
//    public void handle(String command) throws Exception {
//        String[] commands = command.split(" ");
//        if(!commands[1].equals("table")){
//            throw new SyntaxException();
////            System.out.println("语法错误,请重试");
////            return;
//        }
//        String name = commands[2];
//        if(!FileUtil.deleteFile(name)){
//            throw new NoTableException();
//        }
//    }
//}