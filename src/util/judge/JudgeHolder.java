package util.judge;

import util.judge.impl.*;

/**
 * Created by null on 2016/10/27.
 */
public class JudgeHolder {
    private static JudgeOperate equalOprate;
    private static JudgeOperate largeOprate;
    private static JudgeOperate largeOrEqualOprate;
    private static JudgeOperate smallOprate;
    private static JudgeOperate smallOrEqualOprate;
    private static JudgeOperate notEqualOprate;

    static {
        equalOprate = new EqualOprate();
        largeOprate = new LargeOprate();
        largeOrEqualOprate = new LargeOrEqualOprate();
        smallOprate = new SmallOprate();
        smallOrEqualOprate = new SmallOrEqualOprate();
        notEqualOprate = new NotEqualOprate();
    }

    public static JudgeOperate getJudgeOperate(String command){
        if(command.contains(">=")){
            return largeOrEqualOprate;
        }

        if(command.contains("<=")){
            return smallOrEqualOprate;
        }

        if(command.contains(">")){
            return largeOprate;
        }

        if(command.contains("<")){
            return smallOprate;
        }

        if(command.contains("=")){
            return equalOprate;
        }

        if(command.contains("!=")){
            return notEqualOprate;
        }
        return equalOprate;

    }
}
