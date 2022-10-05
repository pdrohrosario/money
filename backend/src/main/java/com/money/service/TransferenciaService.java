package com.money.service;

import com.money.model.*;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import com.money.repository.TransferenciaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService
{
	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	private PoupancaService poupancaService;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TipoGastoService typeSpentService;

	public TransferDetalheDTO search(Long transferId)
	{
		return this.transferenciaRepository.findTransferDetalheById(transferId);
	}

	public List<TransferDTO> findAllTransferByUserName(String userName)
	{
		return this.transferenciaRepository.findTransfersByUserName(userName);
	}

	public Transferencia findTransyId(Long goalId)
	{
		return this.transferenciaRepository.findTransferById(goalId).get();
	}


	public TransferDTO create(TransferDetalheDTO form)
	{
		User user = this.usuarioService.findUserByUserName(form.getUserName());

		if (user == null)
		{
			return null;
		}

		FormaPagamento payment = this.formaPagamentoService.findPaymentWayByName(
			form.getFormaPagamento());
		if (payment == null)
		{
			return null;
		}

		TipoGasto tipoGasto = this.typeSpentService.findTypeSpentByName(form.getTipoGasto());

		if (tipoGasto == null)
		{
			return null;
		}

		if ("POUPANÇA".equals(tipoGasto.getCategory()))
		{
			Poupanca keep = this.poupancaService.findByUsernameAndDate(form.getUserName(),
				form.getData());
			this.transferenciaRepository.saveTransferKeepMoney(form.getId(), keep.getId());
		}

		this.transferenciaRepository.saveTransfer(form.getQuantiaGasta(), form.getDescricao(),
			form.getData(), payment.getId(), tipoGasto.getId(), user.getId());

		return null;
	}

	public boolean delete(Long transferId)
	{
		Optional<Transferencia> transfer = this.transferenciaRepository.findById(transferId);
		if (transfer.isPresent())
		{
			this.transferenciaRepository.delete(transferId);
			return true;
		}
		return false;
	}

	public TransferDTO update(TransferDetalheDTO dto)
	{
		Transferencia transfer = this.findTransyId(dto.getId());

		if (transfer == null)
		{
			return null;
		}

		if (dto.getQuantiaGasta() != null)
		{
			transfer.setQuantia(dto.getQuantiaGasta());
		}

		if (dto.getDescricao() != null && !dto.getDescricao().isEmpty())
		{
			transfer.setDescricao(dto.getDescricao());
		}

		if (dto.getData() != null)
		{
			transfer.setData(dto.getData());
		}

		if (dto.getFormaPagamento() != null)
		{
			FormaPagamento payment = this.formaPagamentoService.findPaymentWayByName(
				dto.getFormaPagamento());
			if (payment == null)
			{
				return null;
			}
			transfer.setFormaPagamento(payment);
		}

		if (dto.getTipoGasto() != null)
		{
			TipoGasto tipoGasto = this.typeSpentService.findTypeSpentByName(dto.getTipoGasto());

			if (tipoGasto == null)
			{
				return null;
			}
			transfer.setTipoGasto(tipoGasto);
		}

		this.transferenciaRepository.update(transfer.getId(), transfer.getDescricao(),
			transfer.getData(), transfer.getFormaPagamento().getId(), transfer.getTipoGasto().getId());

		return new TransferDTO(transfer.getId(), transfer.getQuantia(), transfer.getData(),
			transfer.getTipoGasto().getCategory());
	}
}
