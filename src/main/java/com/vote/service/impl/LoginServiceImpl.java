package com.vote.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vote.mapper.PersonMapper;
import com.vote.mapper.UserloginMapper;
import com.vote.po.Person;
import com.vote.po.Userlogin;
import com.vote.service.LoginService;

/**
 * 
 * @ClassName:LoginServiceImpl
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserloginMapper userloginMapper;
	@Autowired
	private PersonMapper personMapper;
	
	public List<Userlogin> getUsers() throws Exception {
		
		return userloginMapper.selectByExample(null);
	}

	public List<Person> getPersons() throws Exception {
		
		return personMapper.selectByExample(null);
	}

}
