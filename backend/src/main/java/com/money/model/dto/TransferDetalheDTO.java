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

	public void setId(Long id)
	{
		this.id = id;
	}

	public Double getAmountSpent()
	{
		return amountSpent;
	}

	public void setAmountSpent(Double amountSpent)
	{
		this.amountSpent = amountSpent;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public LocalDateTime getTransferDate()
	{
		return transferDate;
	}

	public void setTransferDate(LocalDateTime transferDate)
	{
		this.transferDate = transferDate;
	}

	public String getPaymentWay()
	{
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay)
	{
		this.paymentWay = paymentWay;
	}

	public String getTypeSpent()
	{
		return typeSpent;
	}

	public void setTypeSpent(String typeSpent)
	{
		this.typeSpent = typeSpent;
	}
}
