package com.money.model.dto;

import com.money.model.Objetivo;

public class ObjetivoDTO
{
	private Long id;

	private String titulo;

	private TipoGastoDTO tipoGasto;

	private Double quantia;

	public ObjetivoDTO(Long id, String titulo, TipoGastoDTO tipoGasto, Double quantia)
	{
		this.id = id;
		this.titulo = titulo;
		this.tipoGasto = tipoGasto;
		this.quantia = quantia;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public TipoGastoDTO getTipoGasto()
	{
		return tipoGasto;
	}

	public void setTipoGasto(TipoGastoDTO tipoGasto)
	{
		this.tipoGasto = tipoGasto;
	}

	public Double getQuantia()
	{
		return quantia;
	}

	public void setQuantia(Double quantia)
	{
		this.quantia = quantia;
	}
}
