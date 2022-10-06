package com.money.model.dto;

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

	public Long getId()
	{
		return id;
	}

	public String getNome()
	{
		return nome;
	}
}
