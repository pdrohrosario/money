package com.money.service;

import com.money.model.Goal;
import com.money.model.PlanSpent;
import com.money.model.TypeSpent;
import com.money.model.User;
import com.money.model.dto.*;
import com.money.repository.GoalRepository;
import java.time.LocalDateTime;
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
	private PlanSpentService planSpentService;

	@Autowired
	private UserService userService;

	@Autowired
	private GoalRepository goalRepository;

	public GoalDTO create(GoalDetalheDTO dto)
	{

		if(dto.getUserName() != null && dto.getTypeSpent()!=null)
		{
			User user = this.userService.findUserByUserName(dto.getUserName());

			TypeSpent typeSpent = this.typeSpentService.findTypeSpentByName(dto.getTypeSpent());

			PlanSpentDTO planDto = this.planSpentService.create(new PlanSpentDetalheDTO(dto.getTitle(), dto.getAmount(), dto.getDescription(), dto.getStartDate(), dto.getEndDate()));

			this.goalRepository.saveGoal(planDto.getId(), user.getId(), typeSpent.getId());

			return new GoalDTO(dto.getId(), dto.getTitle(), dto.getTypeSpent(),dto.getAmount());
		}

		return null;
	}

	public List<GoalDTO> listGoalsByUserName(String userName)
	{
		return this.findGoalsByUserName(userName);
	}

	public List<GoalOldenDTO> listOldenGoalsByUserName(String userName)
	{
		return this.goalRepository.findOldenGoalsByUserName(userName, LocalDateTime.now());
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
		Goal goal = this.findGoalById(id);
		if (goal != null)
		{
			this.goalRepository.deleteGoal(id);
			this.planSpentService.delete(goal.getPlanSpent().getId());

			return true;
		}

		return false;
	}

	public GoalDTO update(GoalDetalheDTO dto, Long goalId)
	{
		Goal goal = this.findGoalById(goalId);
		if (goal != null)
		{
			PlanSpentDTO plan = this.planSpentService.update(new PlanSpentDetalheDTO(dto.getTitle(), dto.getAmount(), dto.getDescription(), dto.getStartDate(), dto.getEndDate()));

			TypeSpent typeSpent = this.typeSpentService.findTypeSpentByName(dto.getTypeSpent());

			this.goalRepository.update(typeSpent.getId(), goalId);

			return new GoalDTO(goal.getId(), plan.getTitle(), dto.getTypeSpent(), plan.getAmount());
		}

		return null;
	}

	public GoalStatusDTO verificateGoalStatus(Long id){

		Goal goal = this.findGoalById(id);

		if(goal != null){

			return this.goalRepository.verifyGoalStatus(goal.getId(), goal.getUser().getId());
		}

		return null;
	}

}
