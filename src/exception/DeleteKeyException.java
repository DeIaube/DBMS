package exception;

/**
 * Created by null on 2016/10/23.
 */
public class DeleteKeyException extends Exception {
    public DeleteKeyException() {
        super("主键不可删除");
    }
}
