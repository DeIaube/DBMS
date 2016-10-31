package util.judge.impl;

import util.judge.JudgeOperate;

/**
 * Created by null on 2016/10/27.
 */
public class NotEqualOprate implements JudgeOperate {
    @Override
    public boolean jedge(String d1, String d2) {
        return d1.compareTo(d2) != 0;
    }

    @Override
    public String getOprate() {
        return "!=";
    }
}
