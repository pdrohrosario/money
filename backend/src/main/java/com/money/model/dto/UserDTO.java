package com.money.model.dto;

import com.money.model.User;

public class UserDTO
{
	private Long id;
	private String userName;
	private String email;

	public static synchronized UserDTO create(){
		return new UserDTO();
	}

	public UserDTO withUserName(String userName){
		this.userName = userName;
		return this;
	}

	public UserDTO withId(Long id){
		this.id = id;
		return this;
	}

	public UserDTO withEmail(String email){
		this.email = email;
		return this;
	}

	public Long getId()
	{
		return id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getEmail()
	{
		return email;
	}

}
