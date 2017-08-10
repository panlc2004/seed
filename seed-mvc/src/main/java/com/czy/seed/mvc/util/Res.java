package com.czy.seed.mvc.util;

import java.util.HashMap;

public class Res extends HashMap<String, Object> {

    private static final long serialVersionUID = 915079455546890857L;

	private static final String CODE = "code";      //操作结果编码
	private static final String MSG = "msg";        //操作结果描述
	private static final String DATA = "data";      //返回数据

    public static final int CODE_OK = 200;
    public static final int CODE_ERROR = 500;
    public static final String MSG_OK = "操作成功";
    public static final String MSG_ERROR = "未知异常，请联系管理员";

    public static final String DATA_DEFAULT = "";

	public static Res error() {
		return custom(CODE_ERROR, MSG_ERROR, DATA_DEFAULT);
	}

    public static Res error(String msg) {
        return custom(CODE_ERROR, msg, DATA_DEFAULT);
    }

    public static Res ok() {
        return custom(CODE_OK, MSG_OK, DATA_DEFAULT);
    }

	public static Res ok(Object data) {
		return custom(CODE_OK, MSG_OK, data);
	}

	public static Res custom (int status, String msg, Object data) {
		Res res = new Res();
		res.put(CODE, status).put(MSG, msg).put(DATA, data);
		return res;
	}

    public Res put(String key, Object value) {
		super.put(key, value);
		return this;
	}

}
