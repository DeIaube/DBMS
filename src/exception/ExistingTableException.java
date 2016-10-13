package exception;

/**
 * Created by null on 2016/10/13.
 */
public class ExistingTableException extends Exception {
    public ExistingTableException() {
        super("已存在的表");
    }
}
