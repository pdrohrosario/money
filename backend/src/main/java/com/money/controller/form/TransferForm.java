package com.money.controller.form;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class TransferForm
{
	@NotNull
	private Double amountSpent;

	private String description;

	@NotNull
	private String typeSpent;

	@NotNull
	private LocalDateTime transferDate;

	@NotNull
	private String userName;

	@NotNull
	private String paymentWay;

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

	public void setAmountSpent(Double amountSpent)
	{
		this.amountSpent = amountSpent;
	}

	public LocalDateTime getTransferDate()
	{
		return transferDate;
	}

	public void setTransferDate(LocalDateTime transferDate)
	{
		this.transferDate = transferDate;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setTypeSpent(String typeSpent)
	{
		this.typeSpent = typeSpent;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setPaymentWay(String paymentWay)
	{
		this.paymentWay = paymentWay;
	}
}
