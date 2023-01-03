package com.money.model.dto;

import com.money.model.Transferencia;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.Mapping;

public class TransferDetalheDTO
{
	private Long id;
	private Double quantiaGasta;

	private String descricao;
	private LocalDate data;

	private FormaPagamentoDTO formaPagamento;

	private TipoGastoDTO tipoGasto;

	private Long userId;

	public TransferDetalheDTO(Long id, Double quantiaGasta, String descricao, LocalDate data,
		FormaPagamentoDTO formaPagamento, TipoGastoDTO tipoGasto, Long userId)
	{
		this.id = id;
		this.quantiaGasta = quantiaGasta;
		this.descricao = descricao;
		this.data = data;
		this.formaPagamento = formaPagamento;
		this.tipoGasto = tipoGasto;
		this.userId = userId;
	}

	public TransferDetalheDTO()
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

	public LocalDate getData()
	{
		return data;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setQuantiaGasta(Double quantiaGasta)
	{
		this.quantiaGasta = quantiaGasta;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public void setData(LocalDate data)
	{
		this.data = data;
	}

	public FormaPagamentoDTO getFormaPagamento()
	{
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoDTO formaPagamento)
	{
		this.formaPagamento = formaPagamento;
	}

	public TipoGastoDTO getTipoGasto()
	{
		return tipoGasto;
	}

	public void setTipoGasto(TipoGastoDTO tipoGasto)
	{
		this.tipoGasto = tipoGasto;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getUserId()
	{
		return userId;
	}
}
