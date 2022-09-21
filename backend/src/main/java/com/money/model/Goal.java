package com.money.model;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.*;

@Entity
public class Goal
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private TypeSpent typeSpent;

	private Double amount;

	@ManyToOne
	private User user;

	private String description;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public TypeSpent getTypeSpent()
	{
		return typeSpent;
	}

	public void setTypeSpent(TypeSpent typeSpent)
	{
		this.typeSpent = typeSpent;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
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
