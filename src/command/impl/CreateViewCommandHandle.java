package command.impl;

import command.CommandHandle;
import exception.SyntaxException;
import util.CommandHandleHolder;

/**
 * Created by null on 2016/10/13.
 */
public class CreateViewCommandHandle implements CommandHandle {
    @Override
    public void handle(String command) throws Exception {
        String[] commands = command.split(" ");
        if(!commands[1].equals("view") || !commands[3].equals("as")){
            throw new SyntaxException();
        }
        String name = commands[2];
        String finalCommand = command.substring(command.indexOf("as") + 2, command.length()).trim();
        System.out.println("view:" + name);
        CommandHandleHolder.getSelectCommandHandle().handle(finalCommand);
//        System.out.println(finalCommand);

    }
}
