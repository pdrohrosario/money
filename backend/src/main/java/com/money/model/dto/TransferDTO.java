package com.money.model.dto;

import java.time.LocalDateTime;

public class TransferDTO
{
	private Long id;
	private Double quantiaGasta;
	private LocalDateTime data;
	private String tipoGasto;

	public TransferDTO(Long id, Double quantiaGasta, LocalDateTime data, String tipoGasto)
	{
		this.id = id;
		this.quantiaGasta = quantiaGasta;
		this.data = data;
		this.tipoGasto = tipoGasto;
	}

	public TransferDTO()
	{
	}

	public Long getId()
	{
		return id;
	}

	public Double getQuantiaGasta()
	{
		return quantiaGasta;
	}

	public LocalDateTime getData()
	{
		return data;
	}

	public String getTipoGasto()
	{
		return tipoGasto;
	}
}
