package com.money.service;

import com.money.model.FormaPagamento;
import com.money.model.dto.FormaPagamentoDTO;
import com.money.repository.FormaPagamentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService
{
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	public FormaPagamento findPaymentWayByName(String PaymentWay){
		return this.formaPagamentoRepository.findPaymentWayByName(PaymentWay);
	}

	public List<FormaPagamentoDTO> listPaymentWay()
	{
		return this.formaPagamentoRepository.listPaymentWay();
	}

	public FormaPagamentoDTO create(FormaPagamentoDTO form)
	{
		if(form.getFormaPagamento() == null){
			return null;
		}

		this.formaPagamentoRepository.savePaymentWay(form.getFormaPagamento());

		return form;
	}

	public FormaPagamento findById(Long id){
		return this.formaPagamentoRepository.findById(id).get();
	}

	public FormaPagamentoDTO search(Long id)
	{
		FormaPagamento tp = this.findById(id);

		return new FormaPagamentoDTO(tp.getId(), tp.getCategoria());
	}

	public boolean delete(Long id)
	{
		FormaPagamento to = this.findById(id);

		if(to != null){
			this.formaPagamentoRepository.delete(id);
			return true;
		}

		return false;
	}

	public FormaPagamentoDTO update(FormaPagamentoDTO form)
	{
		FormaPagamento to = this.findById(form.getId());

		if(to != null){
			if(form.getFormaPagamento() == null)
			{
				return null;
			}

			this.formaPagamentoRepository.update(form.getId(), form.getFormaPagamento());

			return form;
		}

		return null;
	}

}
