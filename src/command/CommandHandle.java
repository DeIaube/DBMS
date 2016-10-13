package command;

/**
 * Created by null on 2016/10/9.
 */
public interface CommandHandle {
    void handle(String command) throws Exception;
}
