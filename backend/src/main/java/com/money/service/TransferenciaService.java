package com.money.service;

import com.money.model.*;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import com.money.repository.TransferenciaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
		Optional<Transferencia>  tf = this.transferenciaRepository.findTransferById(goalId);

		return tf.orElse(null);
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

		Long transferenciaId = this.gerarSequencial();

		this.transferenciaRepository.saveTransfer(transferenciaId, form.getQuantiaGasta(), form.getDescricao(),
			form.getData(), payment.getId(), tipoGasto.getId(), user.getId());

		return new TransferDTO(transferenciaId,form.getQuantiaGasta(), form.getData(),
			form.getTipoGasto());
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

	public List<Transferencia>listaTransferenciasPoupancaAtual(String userName){
		return this.transferenciaRepository.listaTransferenciasPoupancaAtiva(userName);
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
			transfer.getTipoGasto().getNome());
	}

	public List<TransferDetalheDTO> findTransferenciasVinculasPoupanca(Long poupancaId)
	{
		List<TransferDetalheDTO> listaTransfPoupanca = new ArrayList<>();

		if(this.poupancaService.findPoupancaById(poupancaId) == null){
			return null;
		}

		List<Long> listaTransferenciaIdVinculados = this.transferenciaRepository.findTransferenciasIdByPoupancaId(poupancaId);

		for(Long transfId : listaTransferenciaIdVinculados){
			listaTransfPoupanca.add(this.transferenciaRepository.findTransferDetalheById(transfId));
		}

		return listaTransfPoupanca;
	}

	public Long gerarSequencial(){
		boolean idExiste = true;
		Long sequencial = null;
		Random random = new Random();
		while (idExiste){
			int numero = random.nextInt(100000);

			if(this.findTransyId((long) numero) == null){
				idExiste = false;
				sequencial = (long) numero;
			};
		}
		return sequencial;
	}
}
