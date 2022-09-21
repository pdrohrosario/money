package com.money.controller.form;

import com.money.model.TypeSpent;
import com.money.model.User;
import java.time.LocalDateTime;
import javax.persistence.ManyToOne;
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
