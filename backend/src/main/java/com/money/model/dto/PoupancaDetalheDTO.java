package com.money.model.dto;

import java.time.LocalDate;

public class PoupancaDetalheDTO
{
	private Long id;

	private String titulo;

	private Double quantia;

	private String descricao;

	private LocalDate dataInicio;

	private LocalDate dataFim;

	private String userName;

	private Integer quantidadeMinimaTransferencias;

	private Double quantiaMesEsperada;

	private Integer quantidadeMes;

	public PoupancaDetalheDTO(){}

	public PoupancaDetalheDTO(Long id, String titulo, Double quantia, String descricao,
		LocalDate dataInicio, LocalDate dataFim, Integer quantidadeMinimaTransferencias,
		Double quantiaMesEsperada, Integer quantidadeMes)
	{
		this.id = id;
		this.titulo = titulo;
		this.quantia = quantia;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.quantidadeMinimaTransferencias = quantidadeMinimaTransferencias;
		this.quantiaMesEsperada = quantiaMesEsperada;
		this.quantidadeMes = quantidadeMes;
	}

	public Integer getQuantidadeMinimaTransferencias()
	{
		return quantidadeMinimaTransferencias;
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

	public LocalDate getDataInicio()
	{
		return dataInicio;
	}

	public LocalDate getDataFim()
	{
		return dataFim;
	}

	public String getUserName()
	{
		return userName;
	}

	public Double getQuantiaMesEsperada()
	{
		return quantiaMesEsperada;
	}

	public Integer getQuantidadeMes()
	{
		return quantidadeMes;
	}
}
