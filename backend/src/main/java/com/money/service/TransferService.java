package com.money.service;

import com.money.model.*;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import com.money.repository.TransferRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService
{
	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private KeepMoneyService keepMoneyService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserService userService;

	@Autowired
	private TypeSpentService typeSpentService;

	public TransferDetalheDTO search(Long transferId)
	{
		return this.transferRepository.findTransferDetalheById(transferId);
	}

	public List<TransferDTO> findAllTransferByUserName(String userName)
	{
		return this.transferRepository.findTransfersByUserName(userName);
	}

	public Transfer findTransyId(Long goalId)
	{
		return this.transferRepository.findTransferById(goalId).get();
	}


	public TransferDTO create( TransferDetalheDTO form)
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

		if("POUPANÇA".equals(typeSpent.getCategory())){
			KeepMoney keep = this.keepMoneyService.findByUsernameAndDate(form.getUserName(), form.getTransferDate());
			this.transferRepository.saveTransferKeepMoney(form.getId(), keep.getId());
		}

		this.transferRepository.saveTransfer(form.getAmountSpent(), form.getDescription(),
			form.getTransferDate(), payment.getId(), typeSpent.getId(), user.getId());

		return null;
	}

	public boolean delete(Long transferId)
	{
		Optional<Transfer> transfer = this.transferRepository.findById(transferId);
		if (transfer.isPresent()){
			this.transferRepository.delete(transferId);
			return true;
		}
		return false;
	}

	public TransferDTO update(TransferDetalheDTO dto)
	{
		Optional<Transfer> transfer = this.transferRepository.findById(dto.getId());

		if(!transfer.isPresent()){
			return null;
		}

		if(dto.getAmountSpent() != null)
		{
			transfer.get().setAmountSpent(dto.getAmountSpent());
		}

		if(dto.getDescription() != null && !dto.getDescription().isEmpty()){
			transfer.get().setDescription(dto.getDescription());
		}

		if(dto.getTransferDate() != null)
		{
			transfer.get().setTransferDate(dto.getTransferDate());
		}

		if(dto.getPaymentWay() != null)
		{
			PaymentWay payment = this.paymentService.findPaymentWayByName(dto.getPaymentWay());
			if (payment == null)
			{
				return null;
			}
			transfer.get().setPaymentWay(payment);
		}


		if(dto.getTypeSpent() != null)
		{
			TypeSpent typeSpent = this.typeSpentService.findTypeSpentByName(dto.getTypeSpent());

			if (typeSpent == null)
			{
				return null;
			}
			transfer.get().setTypeSpent(typeSpent);
		}

		this.transferRepository.update(transfer.get().getId(), transfer.get().getDescription(),
			transfer.get().getTransferDate(), transfer.get().getPaymentWay().getId(),
			transfer.get().getTypeSpent().getId());


		return null;
	}
}
