package com.money.model.dto;

public class GoalDTO
{
	private Long id;

	private String typeSpent;

	private Double amount;

	public Long getId()
	{
		return id;
	}

	public GoalDTO(Long id, Double amount, String typeSpent)
	{
		this.id = id;
		this.amount = amount;
		this.typeSpent = typeSpent;

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
