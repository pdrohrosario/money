package com.money.model;

import javax.persistence.*;

@Entity
public class Objetivo
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	private TipoGasto tipoGasto;

	@OneToOne()
	@JoinColumn(name = "plano_gasto_id",referencedColumnName = "id")
	private PlanoGasto planoGasto;

	@ManyToOne
	private User user;

	public Objetivo(){
		super();
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public TipoGasto getTipoGasto()
	{
		return tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto)
	{
		this.tipoGasto = tipoGasto;
	}

	public PlanoGasto getPlanoGasto()
	{
		return planoGasto;
	}

	public void setPlanoGasto(PlanoGasto planoGasto)
	{
		this.planoGasto = planoGasto;
	}
}
