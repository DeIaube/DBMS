package util;

import bean.DbList;
import com.google.gson.Gson;

import java.io.*;

/**
 * Created by null on 2016/10/9.
 */
public class FileUtil {
    private static String path = "E:\\DBMS\\";

    public static boolean writeToFile(DbList dbList) throws IOException {
        String data = new Gson().toJsonTree(dbList, DbList.class).toString();
        String name = dbList.getName();
        String filePath = path + name;
        File file = new File(filePath);
        if(file.exists()){
            return false;
        }

        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write(data);
        writer.close();
        return true;
    }

    public static String readFromFile(String name) throws IOException {
        String filePath = path + name;
        File file = new File(filePath);
        if(!file.exists()){
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String line = null;
        StringBuilder builder = new StringBuilder();
        while((line = reader.readLine()) != null){
            builder.append(line);
        }
        reader.close();
        return builder.toString();
    }


    public static void coverToFile(DbList dbList) throws IOException {
        String data = new Gson().toJsonTree(dbList, DbList.class).toString();
        String name = dbList.getName();
        String filePath = path + name;
        File file = new File(filePath);
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write(data);
        writer.close();
    }

    public static boolean deleteFile(String name){
        String filePath = path + name;
        File file = new File(filePath);
        if(!file.exists()){
            return false;
        }
        return file.delete();
    }

}
