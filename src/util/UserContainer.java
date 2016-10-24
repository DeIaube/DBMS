package util;

import bean.User;

import java.util.List;

/**
 * Created by null on 2016/10/23.
 */
public class UserContainer {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
