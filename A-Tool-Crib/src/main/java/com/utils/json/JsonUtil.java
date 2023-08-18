package com.utils.json;

import com.alibaba.fastjson.JSON;

import java.util.List;

public final class JsonUtil {

    /**
     * 将实体转化为json
     * @param obj
     * @return
     */
    public static String bean2Json(Object obj){
        return JSON.toJSONString(obj);
    }

    /**
     * 将json字符字体
     * @param jsonStr
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String jsonStr,Class<T> objClass){
        return JSON.parseObject(jsonStr, objClass);
    }

    /**
     * json字符串转化为集合
     * @param jsonStr
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> List<T> json2Array(String jsonStr, Class<T> objClass){
        return JSON.parseArray(jsonStr,objClass);
    }
}
