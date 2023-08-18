package com.utils.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public final class BigDecimalUtil {

    private final static int TENTHOUSAND = 10000;
    private final static int TWO = 2;
    private final static int FOUR = 4;
    private final static Logger LOG = LoggerFactory.getLogger(BigDecimalUtil.class);

    private final static BigDecimal ZERO = BigDecimal.ZERO;
    private final static BigDecimal HUNDRED = new BigDecimal(100);

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal v1,BigDecimal v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        return v1.divide(v2,scale,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 两数相除默认两位小数
     * @param v1 被除数
     * @param v2 除数
     * @return v1/v2保留两位小数
     */
    public static BigDecimal div(BigDecimal v1,BigDecimal v2){
        return div(v1,v2,TWO);
    }

    /**
     * 相加
     */
    public static BigDecimal add(BigDecimal v1,BigDecimal v2){
        if(v1==null && v2==null){
            return ZERO;
        }else if(v1==null){
            return ZERO.add(v2);
        }else if(v2==null){
            return v1.add(ZERO);
        }else {
            return v1.add(v2);
        }
    }

    /**
     * 减法运算
     *
     * @return java.math.BigDecimal
     **/
    public static BigDecimal subtract(BigDecimal v1,BigDecimal v2){
        if(v1 == null && v2 == null){
            return ZERO;
        }else if(v1 == null){
            return ZERO.subtract(v2);
        }else if(v2 == null){
            return v1;
        }else {
            return v1.subtract(v2);
        }
    }

    /**
     * 两数相乘
     */
    public static BigDecimal multiply(BigDecimal v1,BigDecimal v2){
        if(v1 == null || v2 == null){
            return ZERO;
        }
        return v1.multiply(v2);
    }

    /**
     * 空转0
     */
    public static BigDecimal emptyToZero(BigDecimal value){
        return value == null ? BigDecimal.ZERO : value;
    }

    /**
     * 是否为0
     */
    public static boolean isZero(BigDecimal value){
        return BigDecimal.ZERO.compareTo(emptyToZero(value)) == 0;
    }



    /**
     * 计算同比、环比、增长率，(当期-上期)/上期
     */
    public static BigDecimal countRatio(BigDecimal v1,BigDecimal v2){
        if(v2 == null||v2.compareTo(ZERO)==0){
          LOG.info("被除数不能为空或者0");
          return ZERO;
        }
        if(v1==null){
            LOG.info("除数不能为空");
            return ZERO;
        }
        BigDecimal res = calLink(v1,v2);
        return countRatio(res);
    }

    /**
     * 计算占比，部分/总数
     */
    public static BigDecimal countScale(BigDecimal v1,BigDecimal v2){
        if(v2 == null||v2.compareTo(ZERO)==0){
            LOG.info("被除数不能为空或者0");
            return ZERO;
        }
        if(v1==null){
            LOG.info("除数不能为空");
            return ZERO;
        }
        BigDecimal res = div(v1,v2);
        return countRatio(res);
    }


    /**
     * 乘一百计算百分比
     */
    public static BigDecimal countRatio(BigDecimal v1){
        if(v1 == null||v1.compareTo(ZERO)==0){
            LOG.info("被除数不能为空或者0");
            return ZERO;
        }
        return HUNDRED.multiply(v1);
    }

    /**
     * 保留小数
     * @return java.math.BigDecimal
     **/
    public static BigDecimal setDecimalScale(BigDecimal value,int scale){
        if(value == null){
            return BigDecimal.ZERO.setScale(scale,BigDecimal.ROUND_HALF_DOWN);
        }
        return value.setScale(scale,BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * 保留整数
     */
    public static BigDecimal setDecimalScale(BigDecimal value){
        return setDecimalScale(value,0);
    }



    public static BigDecimal countIncreaseRatio(BigDecimal v1,BigDecimal v2){
        if(v1==null){
            LOG.info("除数不能为空");
            return ZERO;
        }
        if(v2 == null||v2.compareTo(ZERO)==0){
            LOG.info("被除数不能为空或者0");
            return ZERO;
        }
        BigDecimal result = div(v1,v2);
        return countRatio(result);
    }
    /*
     * 转化单位为万，保留两位小数
     **/
    public static BigDecimal transferUnitToTenThousand(BigDecimal value){
        if(value == null){
            return BigDecimal.ZERO;
        }
        return div(value,BigDecimal.valueOf(TENTHOUSAND),TWO);
    }

    public static BigDecimal calLink(BigDecimal v1,BigDecimal v2){
        BigDecimal vsub = v1.subtract(v2);
        return div(vsub,v2);
    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
        return div(v1,v2,FOUR);
    }

}
