package com.money.model;


import javax.persistence.*;

@Entity
public class KeepMoney
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private PlanSpent planSpent;

	@ManyToOne
	private User user;

	public PlanSpent getPlanSpent()
	{
		return planSpent;
	}

	public User getUser()
	{
		return user;
	}

	public KeepMoney()
	{
		super();
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
}
