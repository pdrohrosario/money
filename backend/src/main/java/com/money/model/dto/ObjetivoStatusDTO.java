package com.money.model.dto;

import java.time.LocalDateTime;

public class ObjetivoStatusDTO
{
	private String titulo;

	private Double objetivoQuantia;

	private Double quantiaAtual;

	private LocalDateTime dataInicio;

	private LocalDateTime dataFim;

	public ObjetivoStatusDTO(){}

	public ObjetivoStatusDTO(String titulo, Double objetivoQuantia, Double quantiaAtual, LocalDateTime dataInicio,
		LocalDateTime dataFim)
	{
		this.titulo = titulo;
		this.objetivoQuantia = objetivoQuantia;
		this.quantiaAtual = quantiaAtual;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Double getObjetivoQuantia()
	{
		return objetivoQuantia;
	}

	public String getTitulo()
	{
		return titulo;
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
