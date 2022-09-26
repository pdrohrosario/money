package com.money.service;

import com.money.model.Goal;
import com.money.model.dto.GoalDTO;
import com.money.model.dto.GoalDetalheDTO;
import com.money.repository.GoalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GoalService
{
	@Autowired
	private TypeSpentService typeSpentService;

	@Autowired
	private GoalRepository goalRepository;

//	public GoalDTO create(GoalForm form, Long userId)
//	{
//		Goal goal = new Goal();
//		goal.setDescription(form.getDescription());
//		goal.setAmount(form.getAmount());
//		goal.setEndDate(form.getEndDate());
//		goal.getUser().
//
//		if (form.getTypeSpent() != null)
//		{
//			goal.setTypeSpentId(this.typeSpentService.findTypeSpentByName(form.getTypeSpent()));
//		}
//		else
//		{
//			return null;
//		}
//
//		this.goalRepository.saveGoal(goal.getAmount(), goal.getDescription(), goal.getEndDate(),
//			goal.getStartDate(), goal.getUserId(), goal.getTypeSpentId());
//
//		GoalDTO dto = new GoalDTO();
//		dto.setId(goal.getId());
//		dto.setAmount(goal.getAmount());
//		dto.setTypeSpent(form.getTypeSpent());
//
//		return dto;
//	}

	public List<GoalDTO> listGoalsByUserName(String userName)
	{
		return this.findGoalsByUserName(userName);
	}

	public GoalDetalheDTO search(Long id){
		return this.goalRepository.findGoalDetalheById(id);
	}

	public Goal findGoalById(Long goalId)
	{
		return this.goalRepository.findGoalById(goalId).get();
	}

	public List<GoalDTO> findGoalsByUserName(String userName){
		return this.goalRepository.findGoalsByUserName(userName);
	}

	public boolean delete(Long id)
	{
		Optional<Goal> goal = this.goalRepository.findGoalById(id);
		if (goal.isPresent())
		{
			this.goalRepository.deleteGoal(id);
			return true;
		}

		return false;
	}

	public GoalDTO update(GoalDetalheDTO dto, Long goalId)
	{
		Goal goal = this.findGoalById(goalId);
		if (goal != null)
		{
			if (dto.getEndDate() != null)
			{
				goal.setEndDate(dto.getEndDate());
			}
			if (dto.getAmount() != null)
			{
				goal.setAmount(dto.getAmount());
			}
			if (dto.getDescription() != null)
			{
				goal.setDescription(dto.getDescription());
			}

			this.goalRepository.update(goal.getAmount(),
				goal.getDescription(), goal.getEndDate(), goalId);

			return new GoalDTO(goal.getId(), goal.getAmount(), dto.getTypeSpent());
		}

		return null;
	}

}
