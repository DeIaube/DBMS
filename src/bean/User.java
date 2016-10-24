package bean;

import config.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by null on 2016/10/23.
 */
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        tabooList = new ArrayList<>();
    }


    public void addTaboo(Command command){
        tabooList.add(command);
    }

    public void removeTaboo(Command command){
        tabooList.remove(command);
    }

    public String getUsername() {
        return username;
    }

    public static User getUser(String username, String password){
        User user = new User(username, password);
        user.tabooList.add(Command.CREATE_INDEX);
        user.tabooList.add(Command.CREATE);
        user.tabooList.add(Command.DELETE_INDEX);
        user.tabooList.add(Command.DELETE);
        user.tabooList.add(Command.CREATE_MAIN_INDEX);
        user.tabooList.add(Command.ALTER_ADD);
        user.tabooList.add(Command.ALTER_DROP);
        user.tabooList.add(Command.INSERT);
        user.tabooList.add(Command.UPDATE);
        user.tabooList.add(Command.UPDATE_KEY);
        user.tabooList.add(Command.CREATE_USER);
        user.tabooList.add(Command.PERMISSON);
        return user;
    }

    public boolean permisson(Command command){
        return tabooList.contains(command);
    }

    private List<Command> tabooList;
    public boolean login(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
