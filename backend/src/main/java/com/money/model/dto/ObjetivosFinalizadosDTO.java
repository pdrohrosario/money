package com.money.model.dto;

import java.time.LocalDateTime;

public class ObjetivosFinalizadosDTO
{
	private Long id;

	private String titulo;
	private Double objetivoQuantia;
	private Double transferenciaQuantia;
	private String tipoGasto;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	public ObjetivosFinalizadosDTO(){}

	public ObjetivosFinalizadosDTO(Long id, String titulo, Double objetivoQuantia, Double transferenciaQuantia, String tipoGasto,
		LocalDateTime dataInicio, LocalDateTime dataFim)
	{
		this.id = id;
		this.titulo = titulo;
		this.objetivoQuantia = objetivoQuantia;
		this.transferenciaQuantia = transferenciaQuantia;
		this.tipoGasto = tipoGasto;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Long getId()
	{
		return id;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public Double getObjetivoQuantia()
	{
		return objetivoQuantia;
	}

	public Double getTransferenciaQuantia()
	{
		return transferenciaQuantia;
	}

	public String getTipoGasto()
	{
		return tipoGasto;
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
