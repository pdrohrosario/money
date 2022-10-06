package com.money.service;

import com.money.model.Objetivo;
import com.money.model.TipoGasto;
import com.money.model.User;
import com.money.model.dto.*;
import com.money.repository.ObjetivoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoService
{
	@Autowired
	private TipoGastoService typeSpentService;

	@Autowired
	private PlanoGastoService planoGastoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ObjetivoRepository objetivoRepository;

	public ObjetivoDTO create(ObjetivoDetalheDTO dto)
	{

		if (dto.getUserName() != null && dto.getTipoGasto() != null)
		{
			User user = this.usuarioService.findUserByUserName(dto.getUserName());

			TipoGasto tipoGasto = this.typeSpentService.findTypeSpentByName(dto.getTipoGasto());

			Long objetivoId = this.gerarSequencial();

			PlanoGastoDTO planDto = this.planoGastoService.create(
				new PlanoGastoDetalheDTO(dto.getTitulo(), dto.getQuantia(), dto.getDescricao(),
					dto.getDataInicio(), dto.getDataFim()));

			this.objetivoRepository.saveGoal(objetivoId,planDto.getId(), user.getId(), tipoGasto.getId());

			return new ObjetivoDTO(objetivoId, dto.getTitulo(), dto.getTipoGasto(), dto.getQuantia());
		}

		return null;
	}

	public List<ObjetivoDTO> listGoalsByUserName(String userName)
	{
		return this.findGoalsByUserName(userName);
	}

	public List<ObjetivosFinalizadosDTO> listOldenGoalsByUserName(String userName)
	{
		return this.objetivoRepository.findOldenGoalsByUserName(userName, LocalDateTime.now());
	}

	public ObjetivoDetalheDTO search(Long id)
	{
		return this.objetivoRepository.findGoalDetalheById(id);
	}

	public Objetivo findGoalById(Long goalId)
	{
		Optional<Objetivo> obj = this.objetivoRepository.findGoalById(goalId);

		return obj.orElse(null);
	}

	public List<ObjetivoDTO> findGoalsByUserName(String userName)
	{
		return this.objetivoRepository.findGoalsByUserName(userName);
	}

	public boolean delete(Long id)
	{
		Objetivo objetivo = this.findGoalById(id);
		if (objetivo != null)
		{
			this.objetivoRepository.deleteGoal(id);
			this.planoGastoService.delete(objetivo.getPlanoGasto().getId());

			return true;
		}

		return false;
	}

	public ObjetivoDTO update(ObjetivoDetalheDTO dto, Long goalId)
	{
		Objetivo objetivo = this.findGoalById(goalId);
		if (objetivo != null)
		{
			PlanoGastoDTO plan = this.planoGastoService.update(
				new PlanoGastoDetalheDTO(objetivo.getPlanoGasto().getId(),dto.getTitulo(), dto.getQuantia(), dto.getDescricao(),
					dto.getDataInicio(), dto.getDataFim()));

			TipoGasto tipoGasto = this.typeSpentService.findTypeSpentByName(dto.getTipoGasto());

			this.objetivoRepository.update(tipoGasto.getId(), goalId);

			return new ObjetivoDTO(objetivo.getId(), plan.getTitulo(), dto.getTipoGasto(),
				plan.getQuantia());
		}

		return null;
	}

	public ObjetivoStatusDTO verificateGoalStatus(Long id)
	{

		Objetivo objetivo = this.findGoalById(id);

		if (objetivo != null)
		{

			ObjetivoStatusDTO obj = this.objetivoRepository.verifyGoalStatus(objetivo.getId());

			return obj;
		}

		return null;
	}

	public Long gerarSequencial(){
		boolean idExiste = true;
		Long sequencial = null;
		Random random = new Random();
		while (idExiste){
			int numero = random.nextInt(100000);

			if(this.findGoalById((long) numero) == null){
				idExiste = false;
				sequencial = (long) numero;
			};
		}
		return sequencial;
	}

}
