package com.money.service;

import com.money.model.TypeSpent;
import com.money.model.dto.GoalDetalheDTO;
import com.money.model.dto.TypeSpentDTO;
import com.money.repository.TypeSpentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeSpentService
{
	@Autowired
	private TypeSpentRepository typeSpentRepository;

	public TypeSpent findTypeSpentByName(String typeSpent){
		return this.typeSpentRepository.findTypeSpentByName(typeSpent);
	}

	public List<TypeSpentDTO> listTypeSpent()
	{
		return this.typeSpentRepository.listTypeSpent();
	}

	public TypeSpentDTO create(TypeSpentDTO form)
	{
		if(form.getTypeSpent() == null){
			return null;
		}

		this.typeSpentRepository.saveTypeSpent(form.getTypeSpent());

		return form;
	}

	public TypeSpent findById(Long id){
		return this.typeSpentRepository.findById(id).get();
	}

	public TypeSpentDTO search(Long id)
	{
		TypeSpent tp =  this.findById(id);

		return new TypeSpentDTO(tp.getId(), tp.getCategory());
	}

	public boolean delete(Long id)
	{
		TypeSpent to = this.findById(id);

		if(to != null){
			this.typeSpentRepository.delete(id);
			return true;
		}

		return false;
	}

	public TypeSpentDTO update(TypeSpentDTO form)
	{
		TypeSpent to = this.findById(form.getId());

		if(to != null){
			if(form.getTypeSpent() == null)
			{
				return null;
			}

			this.typeSpentRepository.update(form.getId(), form.getTypeSpent());

			return form;
		}

		return null;
	}
}
