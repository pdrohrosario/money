package com.money.service;

import com.money.model.*;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import com.money.repository.TransferenciaRepository;
import io.jsonwebtoken.lang.Assert;
import java.util.*;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
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
	private UsuarioService usuarioService;

	@Autowired
	private TipoGastoService typeSpentService;

	@Autowired
	private ModelMapper modelMapper;

	public Transferencia search(Long transferId)
	{
		return this.findTransferById(transferId);
	}

	public List<TransferDTO> findAllTransferByUserId(Long userId)
	{
		List<Transferencia> transferencias = this.transferenciaRepository.findTransferByUserId(userId);

		Assert.notNull(transferencias,"Transferências do usuário não foram encontradas!");

		return transferencias.stream().map(TransferDTO::new).collect(Collectors.toList());
	}

	public Transferencia findTransferById(Long goalId)
	{
		Optional<Transferencia> tf = this.transferenciaRepository.findTransferById(goalId);

		return tf.orElse(null);
	}


	public TransferDTO create(TransferDetalheDTO form)
	{
		Transferencia transferencia = new Transferencia();
		Assert.notNull(this.formaPagamentoService.findById(form.getFormaPagamento().getId()), "Forma de pagamento não registrada!");
		Assert.notNull(this.usuarioService.findUserById(form.getUserId()), "Erro ao encontrar usuário!");
		Assert.notNull(this.typeSpentService.findById(form.getTipoGasto().getId()), "Tipo de gasto não resgistradp!");

		this.modelMapper.map(form, transferencia);
		transferencia.setId(this.gerarSequencial());

		this.transferenciaRepository.save(transferencia);

		return new TransferDTO(transferencia);
	}

	public boolean delete(Long transferId)
	{
		Optional<Transferencia> transfer = this.transferenciaRepository.findTransferById(transferId);
		Assert.notNull(transfer.get(), "Transferência não registrada!");
		this.transferenciaRepository.delete(transfer.get());
		return true;
	}

	//
//	public List<TransferDTO>listaTransferenciasPoupancaAtual(String userName){
//		return this.transferenciaRepository.listaTransferenciasPoupancaAtiva(userName);
//	}
//
//
	public TransferDTO update(TransferDetalheDTO dto)
	{
		Transferencia tr = this.findTransferById(dto.getId());
		Assert.notNull(tr, "Transferência não registrada!");
		Assert.notNull(this.formaPagamentoService.findById(dto.getFormaPagamento().getId()), "Forma de pagamento não registrada!");
		Assert.notNull(this.usuarioService.findUserById(dto.getUserId()), "Erro ao encontrar usuário!");
		Assert.notNull(this.typeSpentService.findById(dto.getTipoGasto().getId()), "Tipo de gasto não resgistradp!");

		this.modelMapper.map(dto, tr);

		this.transferenciaRepository.save(tr);
		return new TransferDTO(tr);
	}
//
//	public List<TransferDetalheDTO> findTransferenciasVinculasPoupanca(Long poupancaId)
//	{
//		List<TransferDetalheDTO> listaTransfPoupanca = new ArrayList<>();
//
//		if(this.poupancaService.findPoupancaById(poupancaId) == null){
//			return null;
//		}
//
//		List<Long> listaTransferenciaIdVinculados = this.transferenciaRepository.findTransferenciasIdByPoupancaId(poupancaId);
//
//		for(Long transfId : listaTransferenciaIdVinculados){
//			listaTransfPoupanca.add(this.transferenciaRepository.findTransferDetalheById(transfId));
//		}
//
//		return listaTransfPoupanca;
//	}
//
	public Long gerarSequencial()
	{
		boolean idExiste = true;
		Long sequencial = null;
		Random random = new Random();
		while (idExiste)
		{
			int numero = random.nextInt(100000);

			if (this.findTransferById((long) numero) == null)
			{
				idExiste = false;
				sequencial = (long) numero;
			}
			;
		}
		return sequencial;
	}
}
