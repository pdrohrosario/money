package com.money.model.dto;

public class PlanoGastoDTO
{
	private Long id;

	private String titulo;

	private Double quantia;


	public PlanoGastoDTO(Long id, String titulo, Double quantia)
	{
		this.id = id;
		this.titulo = titulo;
		this.quantia = quantia;
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
}
