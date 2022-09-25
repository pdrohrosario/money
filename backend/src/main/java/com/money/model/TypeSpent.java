package com.money.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class TypeSpent
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String category;

	@OneToMany
	private List<Transfer> transfer;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public List<Transfer> getTransfer()
	{
		return transfer;
	}

	public void setTransfer(List<Transfer> transfer)
	{
		this.transfer = transfer;
	}
}
