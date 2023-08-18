package com.utils.result;

/**
 *
 */
public class RtnResult {
    public String code;
    public String msg;
    public Object data;

    public RtnResult() {
    }

    public RtnResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RtnResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /*** 通用成功返回* @return ReturnResult*/
    public static RtnResult success() {
        return new RtnResult("200", "请求成功");
    }

    /*** 通用成功返回* @return ReturnResult*/
    public static RtnResult success(String msg) {
        return new RtnResult("200", msg);
    }

    /*** 通用失败返回* @return ReturnResult*/
    public static RtnResult fail() {
        return new RtnResult("500", "请求失败");
    }

    /*** 通用失败返回* @return ReturnResult*/
    public static RtnResult fail(String msg) {
        return new RtnResult("500", msg);
    }

    /*** 通用成功返回 * @param data 返回的数据* @return ReturnResult*/
    public static RtnResult success(String msg, Object data) {
        return new RtnResult("200", msg, data);
    }
}
