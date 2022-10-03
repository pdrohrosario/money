package com.money.model.dto;

public class TypeSpentDTO
{
	private Long id;

	private String typeSpent;

	public TypeSpentDTO(){}

	public TypeSpentDTO(Long id, String typeSpent)
	{
		this.id = id;
		this.typeSpent = typeSpent;
	}

	public Long getId()
	{
		return id;
	}

	public String getTypeSpent()
	{
		return typeSpent;
	}
}
