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

	public PlanoGasto getPlanoGasto()
	{
		return planoGasto;
	}

	public void setPlanoGasto(PlanoGasto planoGasto)
	{
		this.planoGasto = planoGasto;
	}

	public List<Transferencia> getTransferencias()
	{
		return transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias)
	{
		this.transferencias = transferencias;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Integer getQuantidadeMinimaTransferencias()
	{
		return quantidadeMinimaTransferencias;
	}

	public void setQuantidadeMinimaTransferencias(Integer quantidadeMinimaTransferencias)
	{
		this.quantidadeMinimaTransferencias = quantidadeMinimaTransferencias;
	}

	public Integer getQuantidadeMeses()
	{
		return quantidadeMeses;
	}

	public void setQuantidadeMeses(Integer quantidadeMeses)
	{
		this.quantidadeMeses = quantidadeMeses;
	}

	public Double getQuantiaMesEsperada()
	{
		return quantiaMesEsperada;
	}

	public void setQuantiaMesEsperada(Double quantiaMesEsperada)
	{
		this.quantiaMesEsperada = quantiaMesEsperada;
	}

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
