package com.money.model.dto;

import java.time.LocalDateTime;

public class GoalStatusDTO
{
	private Double goalAmount;

	private Double amount;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	public GoalStatusDTO(Double goalAmount, Double amount, LocalDateTime startDate,
		LocalDateTime endDate)
	{
		this.goalAmount = goalAmount;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Double getGoalAmount()
	{
		return goalAmount;
	}

	public Double getAmount()
	{
		return amount;
	}

	public LocalDateTime getStartDate()
	{
		return startDate;
	}

	public LocalDateTime getEndDate()
	{
		return endDate;
	}
}
