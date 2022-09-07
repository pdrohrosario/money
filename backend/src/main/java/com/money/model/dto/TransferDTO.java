package com.money.model.dto;

import java.time.LocalDateTime;

import com.money.model.Transfer;
import org.springframework.data.domain.Page;

public class TransferDTO
{
	private Long id;
	private Double amountSpent;
	private LocalDateTime transferDate;

	public TransferDTO(Transfer transfer)
	{
		this.id = transfer.getId();
		this.amountSpent = transfer.getAmountSpent();
		this.transferDate = transfer.getTransferDate();
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

	public static Page<TransferDTO> coverter(Page<Transfer> transfers){
		return transfers.map(TransferDTO::new);
	}
}
