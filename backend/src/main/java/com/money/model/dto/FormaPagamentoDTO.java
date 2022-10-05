package com.money.model.dto;

public class FormaPagamentoDTO
{
	private Long id;

	private String formaPagamento;

	public FormaPagamentoDTO(){}

	public FormaPagamentoDTO(Long id, String formaPagamento)
	{
		this.id = id;
		this.formaPagamento = formaPagamento;
	}

	public Long getId()
	{
		return id;
	}

	public String getFormaPagamento()
	{
		return this.formaPagamento;
	}
}
