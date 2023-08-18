package com.utils.data;

import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;

/**
 * @desc 数据脱敏
 */
public final class DataDimUtil {

    /**
     * 对姓名脱敏,保留姓
     * @param name
     * @return
     */
    public final static String getDimName(String name){
        if(StringUtils.isEmpty(name)){
            return name;
        }
        String startStr = name.substring(0,1);
        int size = name.length();
        StringBuilder sb = new StringBuilder();
        sb.append(startStr);
        for(int i=1;i<size;i++){
            sb.append("*");
        }
        return sb.toString();
    }

    /**
     * 电话号码脱敏
     * @param phone
     * @return
     */
    public final static String getDimPhoneNum(String phone){
        if(StringUtils.isEmpty(phone)){
            return phone;
        }
        if(phone.length() < 3){
            return "***";
        }else if(phone.length()<11){
            String startStr = phone.substring(0,3);
            int size = phone.length();
            StringBuilder sb = new StringBuilder();
            sb.append(startStr);
            for(int i=1;i<size;i++){
                sb.append("*");
            }
            return sb.toString();
        }else {
            String startStr = phone.substring(0,3);
            String endStr = phone.substring(7);
            return startStr + "****" + endStr;
        }
    }
}
