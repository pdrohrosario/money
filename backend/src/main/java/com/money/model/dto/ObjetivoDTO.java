package com.money.model.dto;

public class ObjetivoDTO
{
	private Long id;

	private String titulo;
	private String tipoGasto;

	private Double quantia;

	public Long getId()
	{
		return id;
	}

	public ObjetivoDTO(Long id, String titulo, String tipoGasto, Double quantia)
	{
		this.id = id;
		this.titulo = titulo;
		this.tipoGasto = tipoGasto;
		this.quantia = quantia;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public String getTipoGasto()
	{
		return tipoGasto;
	}

	public Double getQuantia()
	{
		return quantia;
	}
}
