package com.money.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class PaymentWay
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String way;

	@OneToMany
	private List<Transfer> transfers;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getWay()
	{
		return way;
	}

	public void setWay(String way)
	{
		this.way = way;
	}

	public List<Transfer> getTransfers()
	{
		return transfers;
	}

	public void setTransfers(List<Transfer> transfers)
	{
		this.transfers = transfers;
	}
}
