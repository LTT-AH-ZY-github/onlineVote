package com.vote.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月21日 下午4:37:43
* @version 1.0
* @类说明
*/
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IPFilter {
	/**
	* 访问ip白名单
	* @return
	*/
	String[] allow() default {};
	
	/**
	* 访问ip黑名单
	* @return
	*/
	String[] deny() default {};
}
