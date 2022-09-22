package com.money.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class Goal
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long typeSpentId;

	private Double amount;

	private String description;

	private Long userId;

	private LocalDateTime startDate = LocalDateTime.now();

	private LocalDateTime endDate;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getTypeSpentId()
	{
		return typeSpentId;
	}

	public void setTypeSpentId(Long typeSpentId)
	{
		this.typeSpentId = typeSpentId;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
}
