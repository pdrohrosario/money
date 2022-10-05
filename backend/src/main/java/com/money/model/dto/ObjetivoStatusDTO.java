package com.money.model.dto;

import java.time.LocalDateTime;

public class ObjetivoStatusDTO
{
	private Double objetivoQuantia;

	private Double quantiaAtual;

	private LocalDateTime dataInicio;

	private LocalDateTime dataFim;

	public ObjetivoStatusDTO(Double objetivoQuantia, Double quantiaAtual, LocalDateTime dataInicio,
		LocalDateTime dataFim)
	{
		this.objetivoQuantia = objetivoQuantia;
		this.quantiaAtual = quantiaAtual;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Double getObjetivoQuantia()
	{
		return objetivoQuantia;
	}

	public Double getQuantiaAtual()
	{
		return quantiaAtual;
	}

	public LocalDateTime getDataInicio()
	{
		return dataInicio;
	}

	public LocalDateTime getDataFim()
	{
		return dataFim;
	}
}
