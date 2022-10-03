package com.money.model.dto;

public class GoalDTO
{
	private Long id;

	private String title;
	private String typeSpent;

	private Double amount;

	public Long getId()
	{
		return id;
	}

	public GoalDTO(Long id, String title, String typeSpent, Double amount)
	{
		this.id = id;
		this.title = title;
		this.typeSpent = typeSpent;
		this.amount = amount;
	}

	public String getTitle()
	{
		return title;
	}

	public String getTypeSpent()
	{
		return typeSpent;
	}

	public Double getAmount()
	{
		return amount;
	}
}
