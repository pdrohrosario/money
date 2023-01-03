package com.money.model.dto;

import java.time.LocalDate;

public class ObjetivoStatusDTO
{
	private String titulo;

	private Double objetivoQuantia;

	private Double quantiaAtual;

	private LocalDate dataInicio;

	private LocalDate dataFim;

	public ObjetivoStatusDTO(){}

	public ObjetivoStatusDTO(String titulo, Double objetivoQuantia, Double quantiaAtual, LocalDate dataInicio,
		LocalDate dataFim)
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

	public LocalDate getDataInicio()
	{
		return dataInicio;
	}

	public LocalDate getDataFim()
	{
		return dataFim;
	}
}
