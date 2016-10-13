package exception;

/**
 * Created by null on 2016/10/13.
 */
public class ExistingParametersException extends Exception {
    public ExistingParametersException() {
        super("已经存在的参数");
    }
}
