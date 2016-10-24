package exception;

/**
 * Created by null on 2016/10/13.
 */
public class ExistingUserException extends Exception {
    public ExistingUserException() {
        super("该用户已存在");
    }
}
