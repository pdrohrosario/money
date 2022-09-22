package com.money.service;

import com.money.controller.form.GoalForm;
import com.money.model.Goal;
import com.money.model.dto.GoalDTO;
import com.money.model.dto.GoalDetalheDTO;
import com.money.repository.GoalRepository;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GoalService
{
	@Autowired
	private TypeSpentService typeSpentService;

	@Autowired
	private GoalRepository goalRepository;

	public GoalDTO create(GoalForm form, Long userId)
	{
		Goal goal = new Goal();
		goal.setDescription(form.getDescription());
		goal.setAmount(form.getAmount());
		goal.setEndDate(form.getEndDate());
		goal.setUserId(userId);

		if (form.getTypeSpent() != null)
		{
			goal.setTypeSpentId(this.typeSpentService.findTypeSpentByName(form.getTypeSpent()));
		}
		else
		{
			return null;
		}

		this.goalRepository.saveGoal(goal.getAmount(), goal.getDescription(), goal.getEndDate(),
			goal.getStartDate(), goal.getUserId(), goal.getTypeSpentId());

		GoalDTO dto = new GoalDTO();
		dto.setId(goal.getId());
		dto.setAmount(goal.getAmount());
		dto.setTypeSpent(form.getTypeSpent());

		return dto;
	}

	public Page<GoalDetalheDTO> findAllGoals(Pageable pageable)
	{
		return this.goalRepository.findAllGoals(pageable);
	}

	public Page<GoalDetalheDTO> findGoalsByUserId(Long userId, Pageable pageable)
	{
		return this.goalRepository.findGoalByUserId(userId,pageable);
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

	public boolean findGoalById(Long id)
	{
		return this.goalRepository.findGoalById(id).isPresent();
	}

	public GoalDTO update(GoalForm form, Long goalId)
	{
		Optional<Goal> goal = this.goalRepository.findGoalById(goalId);
		if (goal.isPresent())
		{
			if (form.getEndDate() != null)
			{
				goal.get().setEndDate(form.getEndDate());
			}
			if (form.getAmount() != null)
			{
				goal.get().setAmount(form.getAmount());
			}
			if (form.getDescription() != null)
			{
				goal.get().setDescription(form.getDescription());
			}
		}

		this.goalRepository.update(goal.get().getAmount(),
			goal.get().getDescription(), goal.get().getEndDate(), goalId);

		GoalDTO dto = new GoalDTO();
		dto.setId(goalId);
		dto.setAmount(goal.get().getAmount());
		dto.setTypeSpent(form.getTypeSpent());

		return dto;
	}

}
