package com.money.model.dto;

import java.time.LocalDateTime;

public class ObjetivoDetalheDTO
{
	private Long id;
	private String titulo;
	private String tipoGasto;
	private String userName;
	private Double quantia;
	private LocalDateTime dataInicio = LocalDateTime.now();
	private LocalDateTime dataFim;
	private String descricao;

	public ObjetivoDetalheDTO(Long id, String titulo, String tipoGasto, String userName, Double quantia,
		LocalDateTime dataInicio, LocalDateTime dataFim, String descricao)
	{
		this.id = id;
		this.titulo = titulo;
		this.tipoGasto = tipoGasto;
		this.userName = userName;
		this.quantia = quantia;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.descricao = descricao;
	}

	public Long getId()
	{
		return id;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public String getTipoGasto()
	{
		return tipoGasto;
	}

	public String getUserName()
	{
		return userName;
	}

	public Double getQuantia()
	{
		return quantia;
	}

	public LocalDateTime getDataInicio()
	{
		return dataInicio;
	}

	public LocalDateTime getDataFim()
	{
		return dataFim;
	}

	public String getDescricao()
	{
		return descricao;
	}
}
