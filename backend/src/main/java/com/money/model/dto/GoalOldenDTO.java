package com.money.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GoalOldenDTO
{
	private Long id;
	private Double goalAmount;
	private Double transferAmount;
	private String typeSpent;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public GoalOldenDTO(){}

	public GoalOldenDTO(Long id, Double goalAmount, Double transferAmount, String typeSpent,
		LocalDateTime startDate, LocalDateTime endDate)
	{
		this.id = id;
		this.goalAmount = goalAmount;
		this.transferAmount = transferAmount;
		this.typeSpent = typeSpent;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId()
	{
		return id;
	}

	public Double getGoalAmount()
	{
		return goalAmount;
	}

	public Double getTransferAmount()
	{
		return transferAmount;
	}

	public String getTypeSpent()
	{
		return typeSpent;
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
