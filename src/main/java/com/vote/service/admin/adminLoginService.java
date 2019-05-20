package com.vote.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vote.po.Person;
import com.vote.po.Userlogin;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月8日 下午2:51:27
* @version 1.0
* @类说明
*/
@Service
public interface adminLoginService {
	public List<Userlogin> getUsers()throws Exception;
	
	public List<Person> getPersons()throws Exception;
}
