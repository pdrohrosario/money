package com.money.model.dto;

import java.time.LocalDateTime;

public class PoupancaDetalheDTO
{
	private Long id;

	private String titulo;

	private Double quantia;

	private String descricao;

	private LocalDateTime dataInicio;

	private LocalDateTime dataFim;

	private String userName;

	public PoupancaDetalheDTO(Long id, String titulo, Double quantia, String descricao,
		LocalDateTime dataInicio, LocalDateTime dataFim, String userName)
	{
		this.id = id;
		this.titulo = titulo;
		this.quantia = quantia;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.userName = userName;
	}

	public PoupancaDetalheDTO(Long id, String titulo, Double quantia, String descricao,
		LocalDateTime dataInicio, LocalDateTime dataFim)
	{
		this.id = id;
		this.titulo = titulo;
		this.quantia = quantia;
		this.descricao = descricao;
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

	public String getUserName()
	{
		return userName;
	}
}
