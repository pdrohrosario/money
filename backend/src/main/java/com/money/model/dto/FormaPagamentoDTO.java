package com.money.model.dto;

public class FormaPagamentoDTO
{
	private Long id;

	private String nome;

	public FormaPagamentoDTO(){}

	public FormaPagamentoDTO(Long id, String nome)
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
		return this.nome;
	}
}
