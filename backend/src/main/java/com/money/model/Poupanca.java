package com.money.model;


import java.util.List;
import javax.persistence.*;

@Entity
public class Poupanca
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private PlanoGasto planoGasto;

	@OneToMany
	List<Transferencia> transferencias;

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
