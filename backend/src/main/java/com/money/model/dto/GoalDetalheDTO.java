package com.money.model.dto;

import com.money.model.Category;
import java.time.LocalDateTime;

public class GoalDetalheDTO
{
	private Long id;

	private Category typeSpent;

	private String userName;
	private Double amount;

	private LocalDateTime startDate = LocalDateTime.now();

	private LocalDateTime endDate;

	private String description;

	public GoalDetalheDTO(Long id, Category typeSpent, String userName, Double amount,
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

	public void setId(Long id)
	{
		this.id = id;
	}

	public Category getTypeSpent()
	{
		return typeSpent;
	}

	public void setTypeSpent(Category typeSpent)
	{
		this.typeSpent = typeSpent;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public LocalDateTime getStartDate()
	{
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate)
	{
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate()
	{
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate)
	{
		this.endDate = endDate;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
