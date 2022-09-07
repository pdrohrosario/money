package com.money.controller.form;

import com.money.model.PaymentWay;
import com.money.model.Transfer;
import com.money.model.TypeSpent;
import com.money.model.User;
import com.money.repository.UserRepository;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class TransferForm
{
	@NotNull
	private Double amountSpent;

	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeSpent typeSpent;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PaymentWay paymentWay;

	@NotNull
	private User user;

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public TypeSpent getTypeSpent()
	{
		return typeSpent;
	}

	public void setTypeSpent(TypeSpent typeSpent)
	{
		this.typeSpent = typeSpent;
	}

	public PaymentWay getPaymentWay()
	{
		return paymentWay;
	}

	public void setPaymentWay(PaymentWay paymentWay)
	{
		this.paymentWay = paymentWay;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Transfer converter(UserRepository repository){
		return new Transfer(amountSpent, description, typeSpent, paymentWay, user);
	}
}
