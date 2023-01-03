package com.money.model;

import com.money.model.dto.TipoGastoDTO;
import java.util.List;
import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
public class TipoGasto
{
	@Id
	private Long id;
	private String nome;

	@OneToOne()
	private Usuario usuario;

	public TipoGasto(Long id, String nome, Usuario usuario)
	{
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
	}

	public TipoGasto()
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

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public TipoGastoDTO converter(){
		return new TipoGastoDTO(id, nome);
	}

}


