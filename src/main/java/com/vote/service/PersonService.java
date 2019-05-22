package com.vote.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vote.po.Person;

/**
 * 
 * @ClassName:PersonService
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
@Service
public interface PersonService {
	public List<Person> getPersons()throws Exception;
}
