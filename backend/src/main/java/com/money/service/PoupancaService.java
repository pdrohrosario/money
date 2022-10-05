package com.money.service;

import com.money.model.Poupanca;
import com.money.model.PlanoGasto;
import com.money.model.User;
import com.money.model.dto.PoupancaDTO;
import com.money.model.dto.PoupancaDetalheDTO;
import com.money.model.dto.PlanoGastoDTO;
import com.money.model.dto.PlanoGastoDetalheDTO;
import com.money.repository.PoupancaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoupancaService
{
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PlanoGastoService planoGastoService;
	@Autowired
	private PoupancaRepository repository;

	public PoupancaDTO create(PoupancaDetalheDTO dto)
	{

		User user = this.usuarioService.findUserByUserName(dto.getUserName());

		if (user != null && this.validaPoupancaAtiva(dto.getDataInicio()))
		{
			PlanoGastoDTO plan = this.planoGastoService.create(
				new PlanoGastoDetalheDTO(dto.getTitulo(), dto.getQuantia(), dto.getDescricao(),
					dto.getDataInicio(), dto.getDataFim()));

			this.repository.saveKeepMoney(plan.getId(), user.getId());

			return new PoupancaDTO(dto.getId(), dto.getTitulo(), dto.getQuantia());
		}
		return null;
	}

	private boolean validaPoupancaAtiva(LocalDateTime dataInicio)
	{
		if (dataInicio == null){
			return false;
		}

		List<Poupanca> poupancas = this.repository.buscaPoupancaAtiva(dataInicio);

		return poupancas.isEmpty();
	}

	public List<PoupancaDTO> listKeepMoneyByUserName(String userName)
	{
		return this.repository.findKeepMoneyByUserName(userName);
	}

	public PoupancaDetalheDTO search(Long id)
	{
		Poupanca poupanca = this.findKeepMoneyById(id);
		PlanoGasto plan = this.planoGastoService.findById(poupanca.getPlanSpent().getId());
		return new PoupancaDetalheDTO(poupanca.getId(), plan.getTitulo(), plan.getQuantia(),
			plan.getDescricao(), plan.getDataInicio(), plan.getDataFim());
	}

	public Poupanca findKeepMoneyById(Long goalId)
	{
		return this.repository.findKeepMoneyById(goalId).get();
	}

	public boolean delete(Long id)
	{
		Poupanca poupanca = this.findKeepMoneyById(id);
		if (poupanca != null)
		{
			this.repository.deleteKeepMoney(id);
			return true;
		}

		return false;
	}

	public PoupancaDTO update(PoupancaDetalheDTO dto)
	{
		Poupanca poupanca = this.findKeepMoneyById(dto.getId());
		if (poupanca != null)
		{
			PlanoGastoDTO plan = this.planoGastoService.update(
				new PlanoGastoDetalheDTO(poupanca.getPlanSpent().getId(), dto.getTitulo(),
					dto.getQuantia(), dto.getDescricao(), dto.getDataInicio(), dto.getDataFim()));

			this.repository.update(plan.getId(), poupanca.getUser().getId());

			return new PoupancaDTO(poupanca.getId(), plan.getTitulo(), plan.getQuantia());
		}

		return null;
	}

	public Poupanca findByUsernameAndDate(String username, LocalDateTime transferDate)
	{
		return this.repository.findByUsernameAndDate(username, transferDate);
	}
}
