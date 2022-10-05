package com.money.model.dto;

import java.time.LocalDateTime;

public class PlanoGastoDetalheDTO
{
	private Long id;

	private String titulo;

	private Double quantia;

	private String descricao;

	private LocalDateTime dataInicio;

	private LocalDateTime dataFim;

	public PlanoGastoDetalheDTO(String titulo, Double quantia, String descricao, LocalDateTime dataInicio,
		LocalDateTime dataFim)
	{
		this.titulo = titulo;
		this.quantia = quantia;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public PlanoGastoDetalheDTO(Long id, String titulo, Double quantia, String descricao, LocalDateTime dataInicio,
		LocalDateTime dataFim){
		this(titulo, quantia, descricao, dataInicio, dataFim);
		this.id = id;
	}


	public Long getId()
	{
		return id;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public Double getQuantia()
	{
		return quantia;
	}

	public String getDescricao()
	{
		return descricao;
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
