package com.money.model.dto;

import java.time.LocalDateTime;

public class PlanSpentDTO
{
	private Long id;

	private String title;

	private Double amount;


	public PlanSpentDTO(Long id, String title, Double amount)
	{
		this.id = id;
		this.title = title;
		this.amount = amount;
	}

	public Long getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public Double getAmount()
	{
		return amount;
	}
}
