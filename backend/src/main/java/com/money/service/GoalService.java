package com.money.service;

import com.money.controller.form.GoalForm;
import com.money.model.Goal;
import com.money.model.TypeSpent;
import com.money.model.User;
import com.money.model.dto.GoalDTO;
import com.money.repository.GoalRepository;
import com.money.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService
{

	@Autowired
	private GoalRepository goalRepository;

	@Autowired
	private UserRepository userRepository;

	public GoalDTO create(GoalForm form, Long userId)
	{
		Optional<User> user = this.userRepository.findUserById(userId);
		Goal goal = new Goal();
		goal.setDescription(form.getDescription());
		goal.setAmount(form.getAmount());
		goal.setEndDate(form.getEndDate());
		goal.setTypeSpent(TypeSpent.valueOf(form.getTypeSpent()));
		goal.setUser(user.get());
		this.goalRepository.save(goal);
		return this.converter(goal);
	}
//	@Autowired
//	private UserService userService;
//
//	public List<UserDTO> findGoalByUser(Long userId)
//	{
//		if(userId == null)
//		{
//			return null;
//		}
//
//		UserDTO user = this.userService.findUserById(userId);
//
//	}

	private GoalDTO converter(Goal goal){
		GoalDTO dto = new GoalDTO();
		dto.setId(goal.getId());
		dto.setAmount(goal.getAmount());
		dto.getTypeSpent(goal.getTypeSpent().name());
		return dto;
	}
}
