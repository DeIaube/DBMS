package util;

import bean.DbList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Lu on 2016/10/11.
 */
public class JudgeUtil {


    public static List<Map<String, String>> judgeEquel(DbList dataList, String conditions){
        if(!conditions.contains("where")){
            return dataList.getDataList();
        }

        conditions = handleConditions(conditions);

        if(conditions.contains("AND") || conditions.contains("and")){
            return andConditionsJudge(dataList, conditions);
        }else if(conditions.contains("OR") || conditions.contains("or")){
            return orConditionsJudge(dataList, conditions);
        }else{
            //单条件
            return singleConditionsJudge(dataList, conditions);
        }

    }

    private static List<Map<String, String>> andConditionsJudge(DbList dataList, String condition) {
        String[] conditions = condition.split(" and ");
        List<Map<String, String>> result = singleConditionsJudge(dataList, conditions[0]);
        for (String s : conditions) {
            List<Map<String, String>> maps = singleConditionsJudge(dataList, s);
            if(maps == null){
                return null;
            }
            result.retainAll(maps);
        }
        return result;
    }

    private static String handleConditions(String conditions) {
        String result = conditions.substring(conditions.indexOf("where") + 5, conditions.length()).trim();
        return result;
    }

    private static List<Map<String, String>> orConditionsJudge(DbList dataList, String condition) {
        String[] conditions = condition.split(" or ");
        List<Map<String, String>> result = new ArrayList<>();
        for (String s : conditions) {
            List<Map<String, String>> maps = singleConditionsJudge(dataList, s);
            if(maps == null){
                return null;
            }
            result.addAll(maps);
        }
        return result;
    }

    private static List<Map<String, String>> singleConditionsJudge(DbList dataList, String conditions) {
        List<Map<String, String>> resule = new ArrayList<>();
        String[] split = conditions.split("=");
        String key = split[0];
        String value = split[1];

        for (Map<String, String> stringStringMap : dataList.getDataList()) {
            if(!dataList.getItemList().contains(key)){
                //不包含的元素
                return null;
            }
            if(stringStringMap.get(key)!=null && stringStringMap.get(key).equals(value)){
                resule.add(stringStringMap);
            }

        }
        return resule;
    }
}
