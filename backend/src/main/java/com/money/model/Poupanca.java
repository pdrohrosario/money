package com.money.model;


import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Entity
public class Poupanca extends PlanoGasto
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantidadeMinimaTransferencias;
	private Integer quantidadeMeses;
	private Double quantiaMesEsperada;

	@OneToMany
	List<Transferencia> transferencias;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public Poupanca(String titulo, Double quantia, String descricao, LocalDate dataInicio,
		LocalDate dataFim, Long id, Integer quantidadeMinimaTransferencias, Integer quantidadeMeses,
		Double quantiaMesEsperada, Usuario usuario)
	{
		super(titulo, quantia, descricao, dataInicio, dataFim);
		this.id = id;
		this.quantidadeMinimaTransferencias = quantidadeMinimaTransferencias;
		this.quantidadeMeses = quantidadeMeses;
		this.quantiaMesEsperada = quantiaMesEsperada;
		this.usuario = usuario;
	}

	public Poupanca()
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

	public List<Transferencia> getTransferencias()
	{
		return transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias)
	{
		this.transferencias = transferencias;
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
