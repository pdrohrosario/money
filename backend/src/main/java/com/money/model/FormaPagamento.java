package com.money.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class FormaPagamento
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@OneToMany
	private List<Transferencia> transferencias;

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
}
