package exception;

/**
 * Created by null on 2016/10/13.
 */
public class ExistingMainIndexException extends Exception {
    public ExistingMainIndexException() {
        super("主索引已存在");
    }
}
