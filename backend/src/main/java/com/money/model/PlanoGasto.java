package com.money.model;

import java.time.LocalDate;
public abstract class PlanoGasto
{

	protected String titulo;

	protected Double quantia;

	protected String descricao;

	protected LocalDate dataInicio;

	protected LocalDate dataFim;

	protected Integer userId;

	public PlanoGasto(String titulo, Double quantia, String descricao, LocalDate dataInicio,
		LocalDate dataFim)
	{
		this.titulo = titulo;
		this.quantia = quantia;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public PlanoGasto(){}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public Double getQuantia()
	{
		return quantia;
	}

	public void setQuantia(Double quantia)
	{
		this.quantia = quantia;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
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

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
}
