package com.money.model.dto;

import java.time.LocalDateTime;

import com.money.model.Transfer;
import org.springframework.data.domain.Page;

public class TransferDTO
{
	private Long id;
	private Double amountSpent;
	private LocalDateTime transferDate;
	private String typeSpent;

	public TransferDTO(Long id, Double amountSpent, LocalDateTime transferDate, String typeSpent)
	{
		this.id = id;
		this.amountSpent = amountSpent;
		this.transferDate = transferDate;
		this.typeSpent = typeSpent;
	}

	public TransferDTO()
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

	public LocalDateTime getTransferDate()
	{
		return transferDate;
	}

	public String getTypeSpent()
	{
		return typeSpent;
	}
}
