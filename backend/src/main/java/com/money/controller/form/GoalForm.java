package com.money.controller.form;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GoalForm
{

	private String typeSpent;

	@NotNull
	private Double amount;

	private String description;

	@NotNull
	@NotBlank
	private LocalDateTime endDate;

	public String getTypeSpent()
	{
		return typeSpent;
	}

	public Double getAmount()
	{
		return amount;
	}

	public String getDescription()
	{
		return description;
	}

	public LocalDateTime getEndDate()
	{
		return endDate;
	}
}
