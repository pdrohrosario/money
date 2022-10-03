package com.money.service;

import com.money.model.PlanSpent;
import com.money.model.TypeSpent;
import com.money.model.dto.PlanSpentDTO;
import com.money.model.dto.PlanSpentDetalheDTO;
import com.money.model.dto.TypeSpentDTO;
import com.money.repository.PlanSpentRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanSpentService
{
	@Autowired
	private PlanSpentRepository repository;

//	public List<TypeSpentDTO> listTypeSpent()
//	{
//		return this.typeSpentRepository.listTypeSpent();
//	}

	public PlanSpentDTO create(PlanSpentDetalheDTO dto)
	{
		PlanSpent tp = this.findById(dto.getId());

		if(tp != null)
		{

			this.repository.savePlanSpent(dto.getTitle(), dto.getAmount(), dto.getDescription(), dto.getStartDate(), dto.getEndDate());

			if(dto.getId() == null){
				PlanSpent plan = this.findByTitleAndStartTitle(dto.getTitle(),dto.getStartDate());
				return new PlanSpentDTO(plan.getId(), dto.getTitle(), dto.getAmount());
			}

			return new PlanSpentDTO(dto.getId(), dto.getTitle(), dto.getAmount());
		}

		return null;

	}

	public PlanSpent findById(Long id){
		return this.repository.findById(id).get();
	}

	public PlanSpent findByTitleAndStartTitle(String title, LocalDateTime startDate){
		return this.repository.findByTitleAndStartTitle(title,startDate).get();
	}

	public PlanSpentDetalheDTO search(Long id)
	{
		PlanSpent plan = this.findById(id);

		return new PlanSpentDetalheDTO(plan.getId(), plan.getTitle(), plan.getAmount(),
			plan.getDescription(), plan.getStartDate(), plan.getEndDate());
	}

	public boolean delete(Long id)
	{
		PlanSpent to = this.findById(id);

		if(to != null){
			this.repository.delete(id);
			return true;
		}

		return false;
	}

	public PlanSpentDTO update(PlanSpentDetalheDTO dto)
	{
		PlanSpent plan = this.findById(dto.getId());

		if(plan != null){

			if (dto.getTitle() !=null){
				plan.setTitle(dto.getTitle());
			}

			if(dto.getAmount() != null){
				plan.setAmount(dto.getAmount());
			}

			if(dto.getDescription() != null){
				plan.setDescription(dto.getDescription());
			}

			if(dto.getStartDate() != null){
				plan.setStartDate(dto.getStartDate());
			}

			if(dto.getEndDate() != null){
				plan.setEndDate(dto.getEndDate());
			}

			this.repository.update(plan.getTitle(), plan.getAmount(), plan.getDescription(), plan.getStartDate(), plan.getEndDate(),
				plan.getId());

			return new PlanSpentDTO(plan.getId(), plan.getTitle(), plan.getAmount());
		}

		return null;
	}
}
