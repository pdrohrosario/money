package com.money.model.dto;

public class KeepMoneyDTO
{
	private Long id;

	private String title;

	private Double amount;

	public Long getId()
	{
		return id;
	}

	public KeepMoneyDTO(Long id, String title, Double amount)
	{
		this.id = id;
		this.title = title;
		this.amount = amount;
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
