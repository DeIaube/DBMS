package exception;

/**
 * Created by null on 2016/10/13.
 */
public class ExistingIndexException extends Exception {
    public ExistingIndexException() {
        super("已存在的索引");
    }
}
