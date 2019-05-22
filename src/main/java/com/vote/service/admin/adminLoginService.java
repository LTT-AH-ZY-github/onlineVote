package com.vote.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vote.po.Person;
import com.vote.po.Userlogin;

/**
 * 
 * @ClassName:adminLoginService
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
@Service
public interface adminLoginService {
	public List<Userlogin> getUsers()throws Exception;
	
	public List<Person> getPersons()throws Exception;
}
