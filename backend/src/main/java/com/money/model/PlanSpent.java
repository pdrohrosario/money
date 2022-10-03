package com.money.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class PlanSpent
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;

	private Double amount;

	private String description;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	@OneToOne
	private Goal goal;

	@OneToOne
	private KeepMoney keepMoney;

	public KeepMoney getKeepMoney()
	{
		return keepMoney;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Goal getGoal()
	{
		return goal;
	}

	public void setGoal(Goal goal)
	{
		this.goal = goal;
	}

	public PlanSpent(String title, Double amount, String description, LocalDateTime startDate,
		LocalDateTime endDate)
	{
		this.title = title;
		this.amount = amount;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public PlanSpent(){}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
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
