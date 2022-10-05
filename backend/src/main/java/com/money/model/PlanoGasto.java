package com.money.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class PlanoGasto
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;

	private Double quantia;

	private String descricao;

	private LocalDateTime dataInicio;

	private LocalDateTime dataFim;

	@OneToOne
	private Objetivo objetivo;

	@OneToOne
	private Poupanca poupanca;


	public PlanoGasto(String titulo, Double quantia, String descricao, LocalDateTime dataInicio,
		LocalDateTime dataFim)
	{
		this.titulo = titulo;
		this.quantia = quantia;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public PlanoGasto(){}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
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

	public LocalDateTime getDataInicio()
	{
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio)
	{
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim()
	{
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim)
	{
		this.dataFim = dataFim;
	}

	public Objetivo getObjetivo()
	{
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo)
	{
		this.objetivo = objetivo;
	}

	public Poupanca getPoupanca()
	{
		return poupanca;
	}

	public void setPoupanca(Poupanca poupanca)
	{
		this.poupanca = poupanca;
	}
}
