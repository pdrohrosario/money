package com.money.model.dto;

import com.money.model.Transferencia;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TransferDTO
{
	private Long id;
	private Double quantiaGasta;
	private LocalDate data;
	private String tipoGasto;

	public TransferDTO(Long id, Double quantiaGasta, LocalDate data, String tipoGasto)
	{
		this.id = id;
		this.quantiaGasta = quantiaGasta;
		this.data = data;
		this.tipoGasto = tipoGasto;
	}

	public TransferDTO()
	{
	}

	public TransferDTO(Transferencia transferencia)
	{
		this.id = transferencia.getId();
		this.quantiaGasta = transferencia.getQuantia();
		this.data = transferencia.getData();
		this.tipoGasto = transferencia.getTipoGasto().getNome();
	}


	public Long getId()
	{
		return id;
	}

	public Double getQuantiaGasta()
	{
		return quantiaGasta;
	}

	public LocalDate getData()
	{
		return data;
	}

	public String getTipoGasto()
	{
		return tipoGasto;
	}
}
