package command.impl;

import bean.User;
import command.BaseCommand;
import config.Command;
import exception.CommandErrorException;
import exception.NoUserException;
import exception.SyntaxException;
import util.CommandSwitchUtil;
import util.UserUtil;

/**
 * Created by null on 2016/10/24.
 *  permisson user add update
    permisson user drop update
 */
public class PermissonChangeCommandHandle extends BaseCommand {
    @Override
    public void handleCommand(String command) throws Exception {
        String username = commands[1];
        Command result = CommandSwitchUtil.switchFromString(commands[3]);
        if(result.equals(Command.ERROR)){
            throw new CommandErrorException();
        }

        for (User user : UserUtil.getUserList()) {
            if(user.getUsername().equals(username)){
                if(commands[2].equals("drop")){
                    user.addTaboo(result);
                }else{
                    user.removeTaboo(result);
                }
                return;
            }
        }

        throw new NoUserException();
    }

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.split(" ");
        if(commands.length != 4){
            throw new SyntaxException();
        }
        if(!commands[2].equals("drop") && !commands[2].equals("add")){
            throw new SyntaxException();
        }
    }


}
