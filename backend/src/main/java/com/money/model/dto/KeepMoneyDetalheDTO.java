package com.money.model.dto;

import java.time.LocalDateTime;

public class KeepMoneyDetalheDTO
{
	private Long id;

	private String title;

	private Double amount;

	private String description;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private String userName;

	public KeepMoneyDetalheDTO(Long id, String title, Double amount, String description,
		LocalDateTime startDate, LocalDateTime endDate, String userName)
	{
		this.id = id;
		this.title = title;
		this.amount = amount;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userName = userName;
	}

	public KeepMoneyDetalheDTO(Long id, String title, Double amount, String description,
		LocalDateTime startDate, LocalDateTime endDate)
	{
		this.id = id;
		this.title = title;
		this.amount = amount;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getDescription()
	{
		return description;
	}

	public LocalDateTime getStartDate()
	{
		return startDate;
	}

	public LocalDateTime getEndDate()
	{
		return endDate;
	}

	public String getUserName()
	{
		return userName;
	}
}
