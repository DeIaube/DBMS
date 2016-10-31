package util.judge;

/**
 * Created by null on 2016/10/27.
 */
public abstract class BaseJudgeOperate implements JudgeOperate{
    protected Long n1;
    protected Long n2;
    protected String d1;
    protected String d2;

    @Override
    public boolean jedge(String d1, String d2) {
        this.d1 = d1;
        this.d2 = d2;
        try {
            n1 = Long.valueOf(d1);
            n2 = Long.valueOf(d2);
        }catch (Exception e){
            n1 = null;
            n2 = null;
        }

        return jedgeNumber();
    }

    public abstract boolean jedgeNumber();
}
