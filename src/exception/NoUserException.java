package exception;

/**
 * Created by null on 2016/10/13.
 */
public class NoUserException extends Exception {
    public NoUserException() {
        super("不存在的用户");
    }
}
