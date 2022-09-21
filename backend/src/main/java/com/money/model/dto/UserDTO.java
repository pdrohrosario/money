package com.money.model.dto;

import com.money.model.User;

public class UserDTO
{
	private Long id;
	private String email;
	private String fullName;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getEmail()
	{
		return email;
	}
}
