package com.czy.seed.mvc.util;

import java.io.Serializable;

public class Res implements Serializable {

    private static final long serialVersionUID = 915079455546890857L;

    private Integer code;
    private String msg;
    private Object data;

    public static final int CODE_OK = 200;
    public static final int CODE_ERROR = 500;
    public static final String MSG_OK = "请求成功";
    public static final String MSG_ERROR = "请求失败";

    public static final String DATA_DEFAULT = "";

    public static Res error() {
        return custom(CODE_ERROR, MSG_ERROR, DATA_DEFAULT);
    }

    public static Res error(Object data) {
        return custom(CODE_ERROR, MSG_ERROR, data);
    }

    public static Res error(String msg, Object data) {
        return custom(CODE_ERROR, msg, data);
    }

    public static Res ok() {
        return custom(CODE_OK, MSG_OK, DATA_DEFAULT);
    }

    public static Res ok(Object data) {
        return custom(CODE_OK, MSG_OK, data);
    }

    public static Res ok(String msg, Object data) {
        return custom(CODE_OK, msg, data);
    }

    public static Res custom(int code, String msg, Object data) {
        Res res = new Res();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
