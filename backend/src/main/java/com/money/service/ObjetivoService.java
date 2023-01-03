package com.money.service;

import com.money.model.Objetivo;
import com.money.model.dto.*;
import com.money.repository.ObjetivoRepository;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
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
	private ModelMapper modelMapper;
	@Autowired
	private ObjetivoRepository objetivoRepository;

//	public ObjetivoDTO create(ObjetivoDetalheDTO dto)
//	{
////		Assert.notNull(this.usuarioService.findUserById(dto.getUserId()), "Usuário não encontrado.");
////		Assert.notNull(this.typeSpentService.findById(dto.getUserId()), "Tipo de gasto não encontrado.");
////
////		Long objetivoId = this.gerarSequencial();
////
////		PlanoGastoDTO planDto = this.planoGastoService.create(
////			new PlanoGastoDetalheDTO(dto.getTitulo(), dto.getQuantia(), dto.getDescricao(),
////				dto.getDataInicio(), dto.getDataFim()));
////
////		this.objetivoRepository.save(new Objetivo(objetivoId, dto.getTipoGasto().getId(), planDto.getId(), dto.getUserId()));
////
////		return new ObjetivoDTO(objetivoId, dto.getTitulo(), dto.getTipoGasto(), dto.getQuantia());
//
//	}

	public List<ObjetivoDTO> listObjetivosByUserId(Long userId)
	{
		List<Objetivo> objetivos = this.objetivoRepository.findObjetivosByUserId(userId);

		if (objetivos.isEmpty())
		{
			return null;
		}

		return objetivos.stream().map(this::convertToObjDTO).collect(Collectors.toList());
	}
//
//	public List<ObjetivosFinalizadosDTO> listOldenGoalsByUserName(String userName)
//	{
//		return this.objetivoRepository.findOldenGoalsByUserName(userName, LocalDate.now());
//	}
//
	public ObjetivoDetalheDTO search(Long id)
	{
		Objetivo obj = this.findObjetivoById(id);

		return this.convertToObjDetalDTO(obj) ;
	}
//
	public Objetivo findObjetivoById(Long goalId)
	{
		Optional<Objetivo> obj = this.objetivoRepository.findObjetivoById(goalId);

		return obj.orElse(null);
	}
//
//	public List<ObjetivoDTO> findGoalsByUserName(String userName)
//	{
//		return null;//this.objetivoRepository.findGoalsByUserName(userName);
//	}
//
//	public boolean delete(Long id)
//	{
//		Objetivo objetivo = this.findGoalById(id);
//		if (objetivo != null)
//		{
//			this.objetivoRepository.deleteGoal(id);
//			this.planoGastoService.delete(objetivo.getPlanoGasto().getId());
//
//			return true;
//		}
//
//		return false;
//	}
//
//	public ObjetivoDTO update(ObjetivoDetalheDTO dto, Long goalId)
//	{
//		Objetivo objetivo = this.findGoalById(goalId);
//		if (objetivo != null)
//		{
//			PlanoGastoDTO plan = this.planoGastoService.update(
//				new PlanoGastoDetalheDTO(objetivo.getPlanoGasto().getId(),dto.getTitulo(), dto.getQuantia(), dto.getDescricao(),
//					dto.getDataInicio(), dto.getDataFim()));
//
//			TipoGasto tipoGasto = this.typeSpentService.findTypeSpentByName(dto.getTipoGasto());
//
//			this.objetivoRepository.update(tipoGasto.getId(), goalId);
//
//			return new ObjetivoDTO(objetivo.getId(), plan.getTitulo(), dto.getTipoGasto(),
//				plan.getQuantia());
//		}
//
//		return null;
//	}
//
//	public ObjetivoStatusDTO verificateGoalStatus(Long id)
//	{
//
//		Objetivo objetivo = this.findGoalById(id);
//
//		if (objetivo != null)
//		{
//
//			ObjetivoStatusDTO obj = this.objetivoRepository.verifyGoalStatus(objetivo.getId());
//
//			return obj;
//		}
//
//		return null;
//	}
//
	public Long gerarSequencial(){
		boolean idExiste = true;
		Long sequencial = null;
		Random random = new Random();
		while (idExiste){
			int numero = random.nextInt(100000);

			if(this.findObjetivoById((long) numero) == null){
				idExiste = false;
				sequencial = (long) numero;
			};
		}
		return sequencial;
	}


	private ObjetivoDetalheDTO convertToObjDetalDTO(Objetivo objetivo){
		return modelMapper.map(objetivo, ObjetivoDetalheDTO.class);
	}

	private ObjetivoDTO convertToObjDTO(Objetivo objetivo){
		return modelMapper.map(objetivo, ObjetivoDTO.class);
	}

}
