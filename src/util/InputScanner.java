package util;

import java.util.Scanner;

/**
 * Created by null on 2016/10/9.
 */
public class InputScanner {

    private Scanner scanner;

    private static InputScanner inputScanner;

    public static InputScanner getSingle(){
        if(inputScanner == null){
            inputScanner = new InputScanner();
        }
        return inputScanner;
    }



    private InputScanner() {
        scanner = new Scanner(System.in);
    }

    public String getInput(){
        String s = scanner.nextLine();
        return s.toLowerCase().trim();
    }

    public boolean hasNext(){
        return scanner.hasNext();
    }
}
