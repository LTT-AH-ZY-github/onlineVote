package com.vote.utils.result;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;

/**
 * 
 * @ClassName:OtherResult
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
public class OtherResult extends Result {

	public Object data = null;
	
	public OtherResult(int code, String msg,Object data) {
		super(code, msg);
		this.data = data;
	}
	
	public static String success(String msg, Object data) {
        return new OtherResult(ResultCommon.CODE_SUCCESS, msg, data).toJson();
    }

    public static String error(String msg, Object data) {
        return new OtherResult(ResultCommon.CODE_ERROR, msg, data).toJson();
    }
    
    public static String xml(String return_code, String return_msg) {
   	 return "<xml><return_code><![CDATA[" + return_code + "]]>" +   
                "</return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";  
   }
    @Override
    public String toJson() {
    	// TODO Auto-generated method stub
    	return String.format("{\"code\": %d, \"msg\": \"%s\", \"data\": %s}", code, msg, new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(data));
    }
    
    @Override
    public Map<String, Object> toMap() {
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", data);
    	return map;
    }

}
