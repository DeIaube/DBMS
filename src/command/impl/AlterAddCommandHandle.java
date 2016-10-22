package command.impl;

import command.BaseCommand;
import exception.ExistingParametersException;
import exception.NoParamException;
import exception.SyntaxException;

/**
 * Created by null on 2016/10/9.
 *  alter add Student (tail integer, sex string)
 */
public class AlterAddCommandHandle extends BaseCommand{
    @Override
    public void handleCommand(String command) throws Exception {
        initDbList();
        for(int i = 3; i < commands.length; i+=2){
            if(dbList.getItemList().contains(commands[i])){
                throw new ExistingParametersException();
            }
            if(!commands[i + 1].equals("string") && !commands[i + 1].equals("integer")){
                throw new NoParamException();
            }
        }

        for(int i = 3; i < commands.length; i+=2){
            dbList.addItem(commands[i], commands[i + 1]);
            dbList.getItemList().add(commands[i]);
        }


    }

    @Override
    public void handleEnd() throws Exception{
        super.handleEnd();
        coverToFile();
    }

    @Override
    public String getCommandName() {
        String name = commands[2];
        return name;
    }

    @Override
    public void checkCommand(String command) throws Exception {
        commands = command.replaceAll("\\,", "").replaceAll("\\(", "").replaceAll("\\)", "").split(" ");
        if(!commands[1].equals("add")){
            throw new SyntaxException();
        }
    }
}









//
//public class AlterAddCommandHandle implements CommandHandle {
//    @Override
//    public void handle(String command) throws Exception {
//        String[] commands = command.replaceAll("\\,", "").replaceAll("\\(", "").replaceAll("\\)", "").split(" ");
//        if(!commands[1].equals("add")){
//            throw new SyntaxException();
//        }
//        String name = commands[2];
//        String data = null;
//        try {
//            data = FileUtil.readFromFile(name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(data == null){
//            throw new NoTableException();
//        }
//
//        DbList dbList = new Gson().fromJson(data, DbList.class);
//
//
//        for(int i = 3; i < commands.length; i+=2){
//            if(dbList.getItemList().contains(commands[i])){
//                throw new ExistingParametersException();
//            }
//            if(!commands[i + 1].equals("string") && !commands[i + 1].equals("integer")){
//                throw new NoParamException();
//            }
//        }
//
//        for(int i = 3; i < commands.length; i+=2){
//            dbList.addItem(commands[i], commands[i + 1]);
//            dbList.getItemList().add(commands[i]);
//        }
//
//        try {
//            FileUtil.coverToFile(dbList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
