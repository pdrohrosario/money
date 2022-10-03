package com.money.model;

import javax.persistence.*;

@Entity
public class Goal
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private TypeSpent typeSpent;

	@OneToOne
	private PlanSpent planSpent;

	@ManyToOne
	private User user;

	public Goal(){
		super();
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setTypeSpent(TypeSpent typeSpent)
	{
		this.typeSpent = typeSpent;
	}

	public void setPlanSpent(PlanSpent planSpent)
	{
		this.planSpent = planSpent;
	}

	public TypeSpent getTypeSpent()
	{
		return typeSpent;
	}

	public PlanSpent getPlanSpent()
	{
		return planSpent;
	}
}
