package com.money.model.dto;

public class UsuarioDTO
{
	private Long id;
	private String userName;
	private String email;

	public static synchronized UsuarioDTO create(){
		return new UsuarioDTO();
	}

	public UsuarioDTO withUserName(String userName){
		this.userName = userName;
		return this;
	}

	public UsuarioDTO withId(Long id){
		this.id = id;
		return this;
	}

	public UsuarioDTO withEmail(String email){
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
