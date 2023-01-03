package com.money.model.dto;

import com.money.model.Usuario;
import javax.validation.constraints.NotNull;

public class UsuarioDetalheDTO
{
	private Long id;
	@NotNull
	private String email;

	@NotNull
	private String name;

	@NotNull
	private String userName;
	@NotNull
	private String password;

	public UsuarioDetalheDTO(Long id, String email, String name, String userName)
	{
		this.id = id;
		this.email = email;
		this.name = name;
		this.userName = userName;
	}

	public static UsuarioDetalheDTO converter(Usuario usuario){
		return new UsuarioDetalheDTO(usuario.getId(),usuario.getEmail(), usuario.getName(),
			usuario.getUserName());
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
