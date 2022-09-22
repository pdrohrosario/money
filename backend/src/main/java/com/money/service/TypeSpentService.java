package com.money.service;

import com.money.repository.TypeSpentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeSpentService
{
	@Autowired
	private TypeSpentRepository typeSpentRepository;

	public Long findTypeSpentByName(String typeSpent){
		return this.typeSpentRepository.findTypeSpentByName(typeSpent);
	}
}
