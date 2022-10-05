package com.money.model.dto;

public class TipoGastoDTO
{
	private Long id;

	private String categoria;

	public TipoGastoDTO(){}

	public TipoGastoDTO(Long id, String typeSpent)
	{
		this.id = id;
		this.categoria = typeSpent;
	}

	public Long getId()
	{
		return id;
	}

	public String getCategoria()
	{
		return categoria;
	}
}
