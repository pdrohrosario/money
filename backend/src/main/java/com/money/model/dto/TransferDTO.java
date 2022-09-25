package com.money.model.dto;

import java.time.LocalDateTime;

import com.money.model.Transfer;
import org.springframework.data.domain.Page;

public class TransferDTO
{
	private Long id;
	private Double amountSpent;
	private LocalDateTime transferDate;

	public TransferDTO(Long id, Double amountSpent, LocalDateTime transferDate, String typeSpent)
	{
		this.id = id;
		this.amountSpent = amountSpent;
		this.transferDate = transferDate;
		this.typeSpent = typeSpent;
	}

	public TransferDTO(){}
	private String typeSpent;

	public String getTypeSpent()
	{
		return typeSpent;
	}

	public void setTypeSpent(String typeSpent)
	{
		this.typeSpent = typeSpent;
	}

	public Long getId()
	{
		return id;
	}

	public Double getAmountSpent()
	{
		return amountSpent;
	}

	public LocalDateTime getTransferDate()
	{
		return transferDate;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setAmountSpent(Double amountSpent)
	{
		this.amountSpent = amountSpent;
	}

	public void setTransferDate(LocalDateTime transferDate)
	{
		this.transferDate = transferDate;
	}
}
