package com.money.service;

import com.money.model.PaymentWay;
import com.money.model.dto.PaymentWayDTO;
import com.money.repository.PaymentWayRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService
{
	@Autowired
	private PaymentWayRepository paymentWayRepository;

	public PaymentWay findPaymentWayByName(String PaymentWay){
		return this.paymentWayRepository.findPaymentWayByName(PaymentWay);
	}

	public List<PaymentWayDTO> listPaymentWay()
	{
		return this.paymentWayRepository.listPaymentWay();
	}

	public PaymentWayDTO create(PaymentWayDTO form)
	{
		if(form.getPaymentWay() == null){
			return null;
		}

		this.paymentWayRepository.savePaymentWay(form.getPaymentWay());

		return form;
	}

	public PaymentWay findById(Long id){
		return this.paymentWayRepository.findById(id).get();
	}

	public PaymentWayDTO search(Long id)
	{
		PaymentWay tp = this.findById(id);

		return new PaymentWayDTO(tp.getId(), tp.getWay());
	}

	public boolean delete(Long id)
	{
		PaymentWay to = this.findById(id);

		if(to != null){
			this.paymentWayRepository.delete(id);
			return true;
		}

		return false;
	}

	public PaymentWayDTO update(PaymentWayDTO form)
	{
		PaymentWay to = this.findById(form.getId());

		if(to != null){
			if(form.getPaymentWay() == null)
			{
				return null;
			}

			this.paymentWayRepository.update(form.getId(), form.getPaymentWay());

			return form;
		}

		return null;
	}

}
