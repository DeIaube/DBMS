package exception;

/**
 * Created by null on 2016/10/13.
 */
public class CommandErrorException extends Exception {
    public CommandErrorException() {
        super("不支持的命令");
    }
}
