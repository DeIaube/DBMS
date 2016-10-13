package exception;

/**
 * Created by null on 2016/10/13.
 */
public class NoTableException extends Exception {
    public NoTableException() {
        super("表不存在");
    }
}
