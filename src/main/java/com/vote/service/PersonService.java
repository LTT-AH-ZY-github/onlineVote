package com.vote.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vote.po.Person;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月9日 下午1:15:45
* @version 1.0
* @类说明
*/
@Service
public interface PersonService {
	public List<Person> getPersons()throws Exception;
}
