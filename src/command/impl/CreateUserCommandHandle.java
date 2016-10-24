package command.impl;

import bean.User;
import command.BaseCommand;
import exception.ExistingUserException;
import exception.SyntaxException;
import util.UserUtil;

import java.util.List;

/**
 * Created by null on 2016/10/23.
 *  create user username password
 */
public class CreateUserCommandHandle extends BaseCommand {

    @Override
    public void handleCommand(String command) throws Exception {
        String username = commands[2];
        String password = commands[3];
        List<User> userList = UserUtil.getUserList();
        for (User user : userList) {
            if(user.getUsername().equals(user)){
                throw new ExistingUserException();
            }
        }
        User user = User.getUser(username, password);
        userList.add(user);
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
    }
}
