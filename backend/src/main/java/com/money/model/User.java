package com.money.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;

	@OneToMany
	private List<Goal> goal = new ArrayList<>();

	@OneToMany
	private List<Transfer> Transfer = new ArrayList<>();
	public User(){}

	public User(String name, String email, String password)
	{
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String getUsername()
	{
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.perfis;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public List<Perfil> getPerfis()
	{
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis)
	{
		this.perfis = perfis;
	}

	public List<Goal> getGoal()
	{
		return goal;
	}

	public void setGoal(List<Goal> goal)
	{
		this.goal = goal;
	}

	public List<com.money.model.Transfer> getTransfer()
	{
		return Transfer;
	}

	public void setTransfer(List<com.money.model.Transfer> transfer)
	{
		Transfer = transfer;
	}
}
