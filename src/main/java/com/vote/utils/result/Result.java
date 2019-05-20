package com.vote.utils.result;

import java.util.HashMap;
import java.util.Map;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月20日 上午1:00:05
* @version 1.0
* @类说明
*/
public class Result implements ResultImpl{
	public int code;
	public String msg;
	
	public Result(int code,String msg) {
		this.code = code;
        this.msg = msg;
	}
	
	/*public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}*/
	/**
     * 用于error方法和success方法的
     */
    public static String success(String msg) {
        return new Result(ResultCommon.CODE_SUCCESS, msg).toJson();
    }

    public static String error(String msg) {
        return new Result(ResultCommon.CODE_ERROR, msg).toJson();
    }

	public String toJson() {
		return String.format("{\"code\": %d, \"msg\": \"%s\"}", code, msg);
	}

	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
}
