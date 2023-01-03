package com.money.model.dto;

import com.money.model.Objetivo;
import java.time.LocalDate;

public class ObjetivoDetalheDTO
{
	private Long id;
	private String titulo;
	private TipoGastoDTO tipoGasto;
	private Long userId;
	private Double quantia;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String descricao;

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

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Double getQuantia()
	{
		return quantia;
	}

	public void setQuantia(Double quantia)
	{
		this.quantia = quantia;
	}

	public LocalDate getDataInicio()
	{
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio)
	{
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim()
	{
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim)
	{
		this.dataFim = dataFim;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
}
