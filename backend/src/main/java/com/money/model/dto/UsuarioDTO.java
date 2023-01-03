package com.money.model.dto;

import com.money.model.Usuario;

public class UsuarioDTO
{
	private Long id;
	private String userName;
	private String email;

	public UsuarioDTO(Long id, String userName, String email)
	{
		this.id = id;
		this.userName = userName;
		this.email = email;
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

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public static UsuarioDTO converter(Usuario usuario){
		return  new UsuarioDTO(usuario.getId(), usuario.getUserName(), usuario.getEmail());
	}

}
