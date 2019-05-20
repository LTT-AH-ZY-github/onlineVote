package com.vote.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vote.mapper.PersonMapper;
import com.vote.po.Person;
import com.vote.service.PersonService;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月9日 下午1:16:31
* @version 1.0
* @类说明
*/
@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonMapper personMapper;
	public List<Person> getPersons() throws Exception {
		
		return personMapper.selectByExample(null);
	}
	
}
