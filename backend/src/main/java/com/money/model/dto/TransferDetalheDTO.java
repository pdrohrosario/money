package com.money.model.dto;

import com.money.model.Transfer;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;

public class TransferDetalheDTO
{
	private Long id;
	private Double amountSpent;

	private String description;
	private LocalDateTime transferDate;

	private String paymentWay;

	private String typeSpent;

	private String userName;

	public TransferDetalheDTO(Long id, Double amountSpent, String description,
		LocalDateTime transferDate, String paymentWay, String typeSpent)
	{
		this.id = id;
		this.amountSpent = amountSpent;
		this.description = description;
		this.transferDate = transferDate;
		this.paymentWay = paymentWay;
		this.typeSpent = typeSpent;
	}

	public TransferDetalheDTO(Transfer transfer)
	{

	}

	public Long getId()
	{
		return id;
	}

	public Double getAmountSpent()
	{
		return amountSpent;
	}

	public String getDescription()
	{
		return description;
	}

	public LocalDateTime getTransferDate()
	{
		return transferDate;
	}

	public String getPaymentWay()
	{
		return paymentWay;
	}

	public String getTypeSpent()
	{
		return typeSpent;
	}

	public String getUserName()
	{
		return userName;
	}
}
