package com.money.service;

import com.money.controller.form.TransferForm;
import com.money.model.PaymentWay;
import com.money.model.TypeSpent;
import com.money.model.User;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import com.money.repository.TransferRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService
{
	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserService userService;

	@Autowired
	private TypeSpentService typeSpentService;

	public TransferDetalheDTO findTransferById(Long transferId)
	{
		return this.transferRepository.findTransferById(transferId);
	}

	public List<TransferDTO> findAllTransferByUserId(Long userId)
	{
		return this.transferRepository.findTransferByUserId(userId);
	}

	public TransferDTO create(TransferForm form)
	{
		User user = this.userService.findUserByUserName(form.getUserName());

		if (user == null)
		{
			return null;
		}

		PaymentWay payment = this.paymentService.findPaymentWayByName(form.getPaymentWay());
		if (payment == null)
		{
			return null;
		}

		TypeSpent typeSpent = this.typeSpentService.findTypeSpentByName(form.getTypeSpent());

		if (typeSpent == null)
		{
			return null;
		}

		this.transferRepository.saveTransfer(form.getAmountSpent(), form.getDescription(),
			form.getTransferDate(), payment.getId(), typeSpent.getId(), user.getId());

		return this.converter(form);
	}

	private TransferDTO converter(TransferForm form)
	{
		TransferDTO dto = new TransferDTO();
		dto.setAmountSpent(form.getAmountSpent());
		dto.setTransferDate(form.getTransferDate());
		dto.setTypeSpent(form.getTypeSpent());

		return dto;
	}

	public boolean delete(Long id)
	{
		return true;
	}

//	public TransferDTO update(TransferForm form, Long id)
//	{
//		return null;
//	}
}
