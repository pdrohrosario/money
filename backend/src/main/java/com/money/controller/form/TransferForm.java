package com.money.controller.form;

import com.money.model.PaymentWay;
import com.money.model.Transfer;
import com.money.model.TypeSpent;
import com.money.model.User;
import com.money.repository.UserRepository;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransferForm
{
	@NotNull
	private Double amountSpent;

	private String description;

	@NotNull
	private String typeSpent;

	@NotNull
	private String paymentWay;

	private Long userId;

	public Double getAmountSpent()
	{
		return amountSpent;
	}

	public String getDescription()
	{
		return description;
	}

	public String getTypeSpent()
	{
		return typeSpent;
	}

	public String getPaymentWay()
	{
		return paymentWay;
	}

	public Long getUserId()
	{
		return userId;
	}

}
