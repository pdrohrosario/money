package com.money.model;

import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Entity
public class Transferencia
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private Double quantia;

	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name="formaPagamento")
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(name = "tipo_gasto_id", referencedColumnName = "id", nullable = false)
	private TipoGasto tipoGasto;

	@ManyToOne
	@JoinColumn(name = "usuario_id",  referencedColumnName = "id", nullable = false)
	private Usuario usuario;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public LocalDate getData()
	{
		return data;
	}

	public void setData(LocalDate data)
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

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

}
