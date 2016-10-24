package util;

import bean.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by null on 2016/10/23.
 */
public class UserUtil {

    private static List<User> userList;
    private static UserContainer userContainer;

    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void checkUserState(){
        if(currentUser == null){
            login();
        }
    }

    public static void login(){
        Scanner scanner = new Scanner(System.in);
        while (currentUser == null){
            System.out.println("请输入用户名");
            String username = scanner.nextLine();
            System.out.println("请输入密码");
            String password = scanner.nextLine();
            for (User user : userList) {
                if(user.login(username, password)){
                    currentUser = user;
                    System.out.println("登陆成功");
                    return;
                }
            }
            for(int i = 0; i < 20; i++){
                System.out.println();
            }

            System.out.println("账号或者密码错误,请重试");

        }



    }

    public static List<User> getUserList() {
        return userList;
    }

    public static void exit(){
        currentUser = null;
        try {
            FileUtil.coverToFile(userContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init(){
        userContainer = null;
        String data = null;
        try {
            data = FileUtil.readFromFile("User");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(data == null){
            userContainer = new UserContainer();
            List<User> userList = new ArrayList<>();
            User root = new User("root", "root");
            userList.add(root);
            userContainer.setUserList(userList);
        }else{
            userContainer = new Gson().fromJson(data, UserContainer.class);
        }
        userList = userContainer.getUserList();
    }
}
