package com.money.model.dto;

import java.time.LocalDateTime;

public class PlanSpentDetalheDTO
{
	private Long id;

	private String title;

	private Double amount;

	private String description;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	public PlanSpentDetalheDTO(String title, Double amount, String description, LocalDateTime startDate,
		LocalDateTime endDate)
	{
		this.title = title;
		this.amount = amount;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public PlanSpentDetalheDTO(Long id, String title, Double amount, String description, LocalDateTime startDate,
		LocalDateTime endDate){
		this(title, amount, description, startDate, endDate);
		this.id = id;
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
}
