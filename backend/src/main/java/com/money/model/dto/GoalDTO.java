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


	public Double getAmount()
	{
		return amount;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTypeSpent(String name)
	{
		return typeSpent;
	}

	public void setTypeSpent(String typeSpent)
	{
		this.typeSpent = typeSpent;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}
}
