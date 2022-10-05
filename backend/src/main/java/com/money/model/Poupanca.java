package com.money.model;


import javax.persistence.*;

@Entity
public class Poupanca
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private PlanoGasto planoGasto;

	@ManyToOne
	private User user;

	private Integer quantidadeMinimaTransferencias;

	private Integer quantidadeMeses;

	private Double quantiaMesEsperada;

	public PlanoGasto getPlanSpent()
	{
		return planoGasto;
	}

	public User getUser()
	{
		return user;
	}

	public Poupanca()
	{
		super();
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
}
