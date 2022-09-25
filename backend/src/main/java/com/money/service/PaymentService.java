package com.money.service;

import com.money.model.PaymentWay;
import com.money.repository.PaymentWayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService
{
	@Autowired
	private PaymentWayRepository paymentWayRepository;

	public PaymentWay findPaymentWayByName(String payment){
		return this.paymentWayRepository.findPaymentWayByName(payment);
	}
}
