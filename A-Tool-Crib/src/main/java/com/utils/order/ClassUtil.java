package com.utils.order;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author silb
 * @date 2022/4/1 15:11
 * @desc 对象类型处理
 */
@Slf4j
public final class ClassUtil {

    /**
     * 转换空字符串,空字符串转化为"-"
     * @param obj
     */
    public static void tranferEmptyStr(Object obj) {
        if (obj == null) {
            return;
        }
        Class<?> clz = obj.getClass();
        Field[] fields = clz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            String fieldName = field.getName();
            Method m = null;
            try {
                m = (Method) obj.getClass().getMethod("get" + getMethodName(fieldName));
                if (field.getGenericType().toString().equals("class java.lang.String")) {
                    // 调用getter方法获取属性值
                    String val = (String) m.invoke(obj);
                    if(StringUtils.isEmpty(val)){
                        Class[] parameterTypes = new Class[1];
                        parameterTypes[0] = field.getType();
                        Method set =  obj.getClass().getMethod("set" + getMethodName(fieldName),parameterTypes);
                        set.invoke(obj,  "-");
                    }

                }
            } catch (NoSuchMethodException e) {
                log.error("no such method",e);
            } catch (IllegalAccessException e) {
                log.error("illegal access",e);
            } catch (InvocationTargetException e) {
                log.error("invocation target",e);
            }
        }
    }

    public static void tranferEmptyToZero(Object obj) {
        if (obj == null) {
            return;
        }
        Class<?> clz = obj.getClass();
        Field[] fields = clz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            String fieldName = field.getName();
            Method m = null;
            try {
                m = (Method) obj.getClass().getMethod("get" + getMethodName(fieldName));
                if (field.getGenericType().toString().equals("class java.lang.String")) {
                    // 调用getter方法获取属性值
                    String val = (String) m.invoke(obj);
                    if(StringUtils.isEmpty(val)){
                        Class[] parameterTypes = new Class[1];
                        parameterTypes[0] = field.getType();
                        Method set =  obj.getClass().getMethod("set" + getMethodName(fieldName),parameterTypes);
                        set.invoke(obj,  "0");
                    }

                }
            } catch (NoSuchMethodException e) {
                log.error("no such method",e);
            } catch (IllegalAccessException e) {
                log.error("illegal access",e);
            } catch (InvocationTargetException e) {
                log.error("invocation target",e);
            }
        }
    }

    /**
     * 获取属性名首字母大写
     *
     * @param fieldName
     * @return
     */
    private static String getMethodName(String fieldName) {
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
