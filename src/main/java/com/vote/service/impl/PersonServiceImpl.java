package com.vote.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vote.mapper.PersonMapper;
import com.vote.po.Person;
import com.vote.service.PersonService;

/**
 * 
 * @ClassName:PersonServiceImpl
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonMapper personMapper;
	public List<Person> getPersons() throws Exception {
		
		return personMapper.selectByExample(null);
	}
	
}
