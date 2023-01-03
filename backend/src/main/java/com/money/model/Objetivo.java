package com.money.model;

import java.time.LocalDate;
import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Entity
public class Objetivo extends PlanoGasto
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "tipo_gasto_id", referencedColumnName = "id", nullable = false)
	private TipoGasto tipoGasto;

	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	private Usuario usuario;

	public Objetivo(String titulo, Double quantia, String descricao, LocalDate dataInicio,
		LocalDate dataFim, Long id, TipoGasto tipoGasto, Usuario usuario)
	{
		super(titulo, quantia, descricao, dataInicio, dataFim);
		this.id = id;
		this.tipoGasto = tipoGasto;
		this.usuario = usuario;
	}

	public Objetivo()
	{

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

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
}
