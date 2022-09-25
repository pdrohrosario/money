package com.money.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class Transfer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime transferDate = LocalDateTime.now();;

	private Double amountSpent;

	private String description;

	@ManyToOne
	private PaymentWay paymentWay;

	@ManyToOne
	private TypeSpent typeSpent;

	@ManyToOne
	private User user;

	public Transfer(){

	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public LocalDateTime getTransferDate()
	{
		return transferDate;
	}

	public void setTransferDate(LocalDateTime transferDate)
	{
		this.transferDate = transferDate;
	}

	public Double getAmountSpent()
	{
		return amountSpent;
	}

	public void setAmountSpent(Double amountSpent)
	{
		this.amountSpent = amountSpent;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public PaymentWay getPaymentWay()
	{
		return paymentWay;
	}

	public void setPaymentWay(PaymentWay paymentWay)
	{
		this.paymentWay = paymentWay;
	}

	public TypeSpent getTypeSpent()
	{
		return typeSpent;
	}

	public void setTypeSpent(TypeSpent typeSpent)
	{
		this.typeSpent = typeSpent;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
