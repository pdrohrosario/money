package com.money.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class Transferencia
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime data = LocalDateTime.now();;

	private Double quantia;

	private String descricao;

	@ManyToOne(optional = false)
	@JoinColumn(name = "forma_pagamento_id")
	private FormaPagamento formaPagamento;

	@ManyToOne(optional = false)
	@JoinColumn(name = "tipo_gasto_id")
	private TipoGasto tipoGasto;

	@ManyToOne
	private User user;

	public Transferencia(){

	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public LocalDateTime getData()
	{
		return data;
	}

	public void setData(LocalDateTime data)
	{
		this.data = data;
	}

	public Double getQuantia()
	{
		return quantia;
	}

	public void setQuantia(Double quantia)
	{
		this.quantia = quantia;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public FormaPagamento getFormaPagamento()
	{
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento)
	{
		this.formaPagamento = formaPagamento;
	}

	public TipoGasto getTipoGasto()
	{
		return tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto)
	{
		this.tipoGasto = tipoGasto;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
