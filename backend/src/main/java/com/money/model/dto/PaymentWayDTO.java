package com.money.model.dto;

public class PaymentWayDTO
{
	private Long id;

	private String paymentWay;

	public PaymentWayDTO(){}

	public PaymentWayDTO(Long id, String paymentWay)
	{
		this.id = id;
		this.paymentWay = paymentWay;
	}

	public Long getId()
	{
		return id;
	}

	public String getPaymentWay()
	{
		return this.paymentWay;
	}
}
