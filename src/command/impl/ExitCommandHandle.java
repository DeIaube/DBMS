package command.impl;

import command.BaseCommand;
import util.UserUtil;

/**
 * Created by null on 2016/10/23.
 */
public class ExitCommandHandle extends BaseCommand {

    @Override
    public void handleCommand(String command) throws Exception {
        UserUtil.exit();
    }

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public void checkCommand(String command) throws Exception {

    }
}
