package com.vote.utils.result;

import java.util.Map;

/**
 * 
 * @ClassName:ResultImpl
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
public interface ResultImpl {
	public String toJson();
	
	public Map<String,Object> toMap();
}
