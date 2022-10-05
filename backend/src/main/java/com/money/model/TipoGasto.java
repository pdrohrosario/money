package com.money.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class TipoGasto
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@OneToMany
	List<Transferencia> transferencias;

	@OneToMany
	private List<Transferencia> transferencia;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Transferencia> getTransferencias()
	{
		return transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias)
	{
		this.transferencias = transferencias;
	}

	public List<Transferencia> getTransferencia()
	{
		return transferencia;
	}

	public void setTransferencia(List<Transferencia> transferencia)
	{
		this.transferencia = transferencia;
	}
}
