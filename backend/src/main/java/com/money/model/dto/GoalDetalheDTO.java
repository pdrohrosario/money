package com.money.model.dto;

import java.time.LocalDateTime;

public class GoalDetalheDTO
{
	private Long id;

	private String typeSpent;

	private String userName;
	private Double amount;

	private LocalDateTime startDate = LocalDateTime.now();

	private LocalDateTime endDate;

	private String description;

	public GoalDetalheDTO(Long id, String typeSpent, String userName, Double amount,
		LocalDateTime startDate, LocalDateTime endDate, String description)
	{
		this.id = id;
		this.typeSpent = typeSpent;
		this.userName = userName;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	public Long getId()
	{
		return id;
	}

	public String getTypeSpent()
	{
		return typeSpent;
	}

	public String getUserName()
	{
		return userName;
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

	public String getDescription()
	{
		return description;
	}
}
