package com.money.model.dto;

public class PoupancaDTO
{
	private Long id;

	private String titulo;

	private Double quantia;

	public Long getId()
	{
		return id;
	}

	public PoupancaDTO(Long id, String titulo, Double quantia)
	{
		this.id = id;
		this.titulo = titulo;
		this.quantia = quantia;
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
