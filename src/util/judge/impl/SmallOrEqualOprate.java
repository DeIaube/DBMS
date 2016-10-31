package util.judge.impl;

import util.judge.BaseJudgeOperate;

/**
 * Created by null on 2016/10/27.
 */
public class SmallOrEqualOprate extends BaseJudgeOperate {

    @Override
    public boolean jedgeNumber() {
        if(n1 != null && n2 != null){
            return n1 <= n2;
        }
        return d1.compareTo(d2) <= 0;
    }


    @Override
    public String getOprate() {
        return "<=";
    }
}
