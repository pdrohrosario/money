package com.money.model.dto;

import com.money.model.User;

public class UserDTO
{
	private Long id;
	private String userName;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
