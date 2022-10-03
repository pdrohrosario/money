package com.money.service;

import com.money.model.KeepMoney;
import com.money.model.PlanSpent;
import com.money.model.User;
import com.money.model.dto.KeepMoneyDTO;
import com.money.model.dto.KeepMoneyDetalheDTO;
import com.money.model.dto.PlanSpentDTO;
import com.money.model.dto.PlanSpentDetalheDTO;
import com.money.repository.KeepMoneyRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeepMoneyService
{
	@Autowired
	private UserService userService;

	@Autowired
	private PlanSpentService planSpentService;
	@Autowired
	private KeepMoneyRepository repository;

	public KeepMoneyDTO create(KeepMoneyDetalheDTO dto)
	{

		User user = this.userService.findUserByUserName(dto.getUserName());

		if (user != null)
		{
			PlanSpentDTO plan = this.planSpentService.create(
				new PlanSpentDetalheDTO(dto.getTitle(), dto.getAmount(), dto.getDescription(),
					dto.getStartDate(), dto.getEndDate()));

			this.repository.saveKeepMoney(plan.getId(), user.getId());

			return new KeepMoneyDTO(dto.getId(), dto.getTitle(), dto.getAmount());
		}
		return null;
	}

	public List<KeepMoneyDTO> listKeepMoneyByUserName(String userName)
	{
		return this.repository.findKeepMoneyByUserName(userName);
	}

	public KeepMoneyDetalheDTO search(Long id)
	{
		KeepMoney keepMoney = this.findKeepMoneyById(id);
		PlanSpent plan = this.planSpentService.findById(keepMoney.getPlanSpent().getId());
		return new KeepMoneyDetalheDTO(keepMoney.getId(), plan.getTitle(), plan.getAmount(),
			plan.getDescription(), plan.getStartDate(), plan.getEndDate());
	}

	public KeepMoney findKeepMoneyById(Long goalId)
	{
		return this.repository.findKeepMoneyById(goalId).get();
	}

	public boolean delete(Long id)
	{
		KeepMoney keepMoney = this.findKeepMoneyById(id);
		if (keepMoney != null)
		{
			this.repository.deleteKeepMoney(id);
			return true;
		}

		return false;
	}

	public KeepMoneyDTO update(KeepMoneyDetalheDTO dto)
	{
		KeepMoney keepMoney = this.findKeepMoneyById(dto.getId());
		if (keepMoney != null)
		{
			PlanSpentDTO plan = this.planSpentService.update(
				new PlanSpentDetalheDTO(keepMoney.getPlanSpent().getId(), dto.getTitle(),
					dto.getAmount(), dto.getDescription(), dto.getStartDate(), dto.getEndDate()));

			this.repository.update(plan.getId(), keepMoney.getUser().getId());

			return new KeepMoneyDTO(keepMoney.getId(), plan.getTitle(), plan.getAmount());
		}

		return null;
	}

	public KeepMoney findByUsernameAndDate(String username, LocalDateTime transferDate){
		return this.repository.findByUsernameAndDate(username, transferDate);
	}
}
