package com.money.model.dto;

import com.money.model.Transferencia;
import java.time.LocalDateTime;

public class TransferDetalheDTO
{
	private Long id;
	private Double quantiaGasta;

	private String descricao;
	private LocalDateTime data;

	private String formaPagamento;

	private String tipoGasto;

	private String userName;

	public TransferDetalheDTO(Long id, Double quantiaGasta, String descricao,
		LocalDateTime data, String formaPagamento, String tipoGasto)
	{
		this.id = id;
		this.quantiaGasta = quantiaGasta;
		this.descricao = descricao;
		this.data = data;
		this.formaPagamento = formaPagamento;
		this.tipoGasto = tipoGasto;
	}

	public TransferDetalheDTO(Transferencia transferencia)
	{

	}

	public Long getId()
	{
		return id;
	}

	public Double getQuantiaGasta()
	{
		return quantiaGasta;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public LocalDateTime getData()
	{
		return data;
	}

	public String getFormaPagamento()
	{
		return formaPagamento;
	}

	public String getTipoGasto()
	{
		return tipoGasto;
	}

	public String getUserName()
	{
		return userName;
	}
}
