package com.money.model.dto;

import com.money.model.TipoGasto;

public class TipoGastoDTO
{
	private Long id;

	private String nome;

	public TipoGastoDTO(){}

	public TipoGastoDTO(Long id, String nome)
	{
		this.id = id;
		this.nome = nome;
	}

	public TipoGastoDTO(TipoGasto tipoGasto)
	{
		this.id = tipoGasto.getId();
		this.nome = tipoGasto.getNome();
	}


	public Long getId()
	{
		return id;
	}

	public String getNome()
	{
		return nome;
	}
}
